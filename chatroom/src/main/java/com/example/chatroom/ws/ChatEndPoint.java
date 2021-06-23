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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
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
    private static Map<Integer,ChatEndPoint> onlineUsers=new ConcurrentHashMap<>();

    //通过session 对象，发送消息给指定的用户
    private Session session;

    //之前在httpSession存储了用户资料
    private HttpSession httpSession;

    private String username;

    private Integer userId;
    //建立时调用
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        //获取httpSession,直接获取将无法获得
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        this.session=session;
        username= (String) this.httpSession.getAttribute("username");
        userId= (Integer) this.httpSession.getAttribute("userId");
        this.httpSession.setAttribute("onlineUsers",onlineUsers);
        //将ChatEndPoint存储到容器中，因为当前用户在线。
        onlineUsers.put(userId,this);
        //发送系统消息
        broadcastSystemMessage(username+"已上线");
    }


    //接收客户端发送的数据时被调用
    @OnMessage public void onMessage(String message) {
            BrowserToServerMessage mess=JSON.parseObject(message, BrowserToServerMessage.class);
            System.out.println("mess: "+mess);
            if (mess.getIsPrivateMessage()){
                log.info("private message");
                log.info("received message:"+mess.toString());
                broadcastToUser(mess.getToUserId(),mess.getFromUserId(),mess.getMessage());
            }else {
                log.info("groupChat message");
                log.info("received message:"+mess.toString());
                broadcastToGroupChat(mess.getMessage(),mess.getGroupChatId(),mess.getFromUserId());
            }
    }

    //连接关闭时调用
    @OnClose
    public void onClose(Session session){
//        onlineUsers.remove(username);
//        ServerToBrowserMessage serverToBrowserMessage= new ServerToBrowserMessage(true,1,username,getOnlineUsers(),"离线了",new Date());
//        serverToBrowserMessageService.add(serverToBrowserMessage);
//        String message=JSON.toJSONString(serverToBrowserMessage);
//        broadcastAllUsers(message);
//        String username = (String) httpSession.getAttribute("username");
//        if (username != null){
//            onlineUsers.remove(username);
//        }
//        httpSession.removeAttribute("user");

    }


    //获取所有在线用户名
    private List<Integer> getOnlineUsers(){
        Set<Integer> keySet=onlineUsers.keySet();
        List<Integer> temp=new ArrayList<>();
        for (Integer onlineUser:keySet){
            temp.add(onlineUser);
        }
        return temp;
    }


    //广播给在线用户
    private void broadcastAllUsers(String message) {
        //要将该消息推送给所有的客户端
        Set<Integer> users=onlineUsers.keySet();
        for (Integer id:users) {
            ChatEndPoint chatEndPoint=onlineUsers.get(id);
            try {
                chatEndPoint.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送群聊信息
     * @param message
     * @param groupChatId
     * @param fromUserId
     */
    private void broadcastToGroupChat(String message,Integer groupChatId , Integer fromUserId) {
       ServerToBrowserMessage serverToBrowserMessage = new ServerToBrowserMessage(fromUserId,null,groupChatId,1,false,false,message,new Date());
        GroupChat groupChat = groupChatService.findOne(groupChatId);
        List<User>userList = groupChat.getGroupChatUsers();
        for (User user : userList){
            if (user.getUserId() != fromUserId) {//避开自己
                String sendMessage = JSON.toJSONString(serverToBrowserMessage);
                sendMessage(user.getUserId(),sendMessage);
                //当发送对象在线时才发送以及
                serverToBrowserMessageService.add(serverToBrowserMessage);
            }
        }

    }

    /**
     * 发送系统信息
     * @param message
     */
    private void broadcastSystemMessage(String message){
        ServerToBrowserMessage serverToBrowserMessage = new ServerToBrowserMessage(userId,null,1,1,true,false,message,new Date());
        GroupChat groupChat = groupChatService.findOne(1);
        List<User> userList = groupChat.getGroupChatUsers();
        for (User user: userList){
            serverToBrowserMessage.setToUserId(user.getUserId());
            String sendMessage = JSON.toJSONString(serverToBrowserMessage);
            //只有在线的才发送
            sendMessage(user.getUserId(),sendMessage);
            serverToBrowserMessageService.add(serverToBrowserMessage);
        }
    }

    /**
     * 发给用户信息
     * @param toUserId
     * @param fromUserId
     * @param message
     */
    public void broadcastToUser(Integer toUserId,Integer fromUserId , String message){
        ServerToBrowserMessage serverToBrowserMessage = new ServerToBrowserMessage(fromUserId,toUserId,1,false,true,message,new Date());
        String sendMessage = JSON.toJSONString(serverToBrowserMessage);
        sendMessage(toUserId,sendMessage);
        serverToBrowserMessageService.add(serverToBrowserMessage);
    }

    private void sendMessage(Integer userId,String message){
        if (onlineUsers.get(userId) != null){
            try {
                log.info("能发送信息："+message);
                ChatEndPoint chatEndPoint = onlineUsers.get(userId);
                chatEndPoint.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
