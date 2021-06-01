package com.example.chatroom.ws;


import com.alibaba.fastjson.JSON;
import com.example.chatroom.config.GetHttpSessionConfigurator;
import com.example.chatroom.controller.JsonController;
import com.example.chatroom.entity.BrowserToServerMessage;
import com.example.chatroom.entity.GroupChat;
import com.example.chatroom.entity.ServerToBrowserMessage;
import com.example.chatroom.entity.User;
import com.example.chatroom.service.impl.BrowserToServerMessageServiceImpl;
import com.example.chatroom.service.impl.GroupChatServiceImpl;
import com.example.chatroom.service.impl.LoginResultServiceImpl;
import com.example.chatroom.service.impl.ServerToBrowserMessageServiceImpl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//需要配置configurator
@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfigurator.class)
@Component//交给Spring管理,但是只是加上注解没有用,需要配置
//每个客户端都会有一个ChatEndPoint，通过session 发送数据
public class ChatEndPoint {

    public static LoginResultServiceImpl loginResultService;
    public static GroupChatServiceImpl groupChatService;
    public static ServerToBrowserMessageServiceImpl serverToBrowserMessageService;
    public static BrowserToServerMessageServiceImpl browserToServerMessageService;
    //用来存储每一个客户端对象对应的ChatEndPoint对象
    //考虑到线程安全使用ConcurrentHashMap #并发编程
    private static Map<String,ChatEndPoint> onlineUsers=new ConcurrentHashMap<>();

    //通过session 对象，发送消息给指定的用户
    private Session session;

    //之前在httpSession存储了用户资料
    private HttpSession httpSession;

    private String username;
    //建立时调用
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        this.session=session;
        //获取httpSession存储的user对象
        username= (String) this.httpSession.getAttribute("username");
        Integer userId= (Integer) this.httpSession.getAttribute("userId");
        this.httpSession.setAttribute("onlineUsers",onlineUsers);
        Date loginTime=loginResultService.findLatestLoginTime(userId);
        //将ChatEndPoint存储到容器中，因为当前用户在线。
        onlineUsers.put(username,this);
        //将当前在线用户信息推送给所有客户端
        //1.获取消息
        ServerToBrowserMessage serverToBrowserMessage= new ServerToBrowserMessage(true,1,username,getOnlineUsers(),"上线了",new Date());
        //String message=MessageUtils.getMessage(true,username,"上线了",loginTime,getOnlineUsers());
        serverToBrowserMessageService.add(serverToBrowserMessage);//存储信息
        String message=JSON.toJSONString(serverToBrowserMessage);
        //2.调用方法进行系统消息推送
        broadcastAllUsers(message);
    }


    //接收客户端发送的数据时被调用
    @OnMessage public void onMessage(String message) {
        //将message 转换成message对象
        try {
            BrowserToServerMessage mess=JSON.parseObject(message, BrowserToServerMessage.class);
            browserToServerMessageService.add(mess);
            System.out.println("mess: "+mess);
            Integer groupChatId;
            //如果有群聊,就找到群的用户
            if (mess.getGroupChatId()!=null){
                groupChatId=mess.getGroupChatId();
            }else {
                groupChatId=null;
            }

            List<String> toNames=new ArrayList<>();
            if (groupChatId!=null){
                GroupChat groupChat=groupChatService.findOne(groupChatId);
                List<User>users=groupChat.getGroupChatUsers();
                for (User user:users) {
                    //获取群组里目前在线的人
                    if (onlineUsers.get(user.getUsername())!=null){
                        toNames.add(user.getUsername());
                        System.out.println(user.getUsername());
                    }
                }
                //把自己移除
                toNames.remove(username);
            }else {
                toNames=mess.getToUsernames();
            }
            //获取要将数据发送的用户
            String fromName=mess.getFromUser();
            //获取消息数据
            String data = mess.getMessage();
            //获取当前登入的用户
            for (String toName:toNames) {
                List<String> toUser=new ArrayList<>();
                toUser.add(toName);
                //获取推送给指定用户消息格式的数据
                ServerToBrowserMessage serverToBrowserMessage= new ServerToBrowserMessage(false,groupChatId,fromName,toUser,data,new Date());
                serverToBrowserMessageService.add(serverToBrowserMessage);
                //String resultMessage = MessageUtils.getMessage(false, fromName,data,new Date(),toName);
                String resultMessage=JSON.toJSONString(serverToBrowserMessage);
                System.out.println("发送消息的格式："+resultMessage);
                onlineUsers.get(toName).session.getBasicRemote().sendText(resultMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //连接关闭时调用
    @OnClose
    public void onClose(Session session){
        onlineUsers.remove(username);
        ServerToBrowserMessage serverToBrowserMessage= new ServerToBrowserMessage(true,1,username,getOnlineUsers(),"离线了",new Date());
        serverToBrowserMessageService.add(serverToBrowserMessage);
        String message=JSON.toJSONString(serverToBrowserMessage);
        broadcastAllUsers(message);
        String username = (String) httpSession.getAttribute("username");
        if (username != null){
            onlineUsers.remove(username);
        }
        httpSession.removeAttribute("user");

    }


    //获取所有在线用户名
    private List<String> getOnlineUsers(){
        Set<String> keySet=onlineUsers.keySet();
        List<String> temp=new ArrayList<>();
        for (String onlineUser:keySet){
            temp.add(onlineUser);
        }

        return temp;
    }


    //广播给在线用户
    private void broadcastAllUsers(String message) {
        //要将该消息推送给所有的客户端
        Set<String> names=onlineUsers.keySet();
        for (String name:names) {
            ChatEndPoint chatEndPoint=onlineUsers.get(name);
            try {
                chatEndPoint.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
