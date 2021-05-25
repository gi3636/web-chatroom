package com.example.chatroom.ws;


import com.alibaba.fastjson.JSON;
import com.example.chatroom.config.GetHttpSessionConfigurator;
import com.example.chatroom.entity.Message;
import com.example.chatroom.entity.User;
import com.example.chatroom.service.impl.LoginResultServiceImpl;
import com.example.chatroom.utils.MessageUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

//需要配置configurator
@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfigurator.class)
@Component//交给Spring管理,但是只是加上注解没有用,需要配置
//每个客户端都会有一个ChatEndPoint，通过session 发送数据
public class ChatEndPoint {

    public static LoginResultServiceImpl loginResultService;

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
        this.session=session;
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        //获取httpSession存储的user对象
        username= (String) this.httpSession.getAttribute("username");
        Integer userId= (Integer) this.httpSession.getAttribute("userId");
        this.httpSession.setAttribute("onlineUsers",onlineUsers);
        Date loginTime=loginResultService.findLatestLoginTime(userId);
        System.out.println(username);
        System.out.println(loginTime);
        //将ChatEndPoint存储到容器中，因为当前用户在线。
        onlineUsers.put(username,this);
        //将当前在线用户信息推送给所有客户端
        //1.获取消息
        String message=MessageUtils.getMessage(true,username,"上线了",loginTime,getOnlineUsers());
        //2.调用方法进行系统消息推送
        broadcastAllUsers(message);
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

    //获取所有在线用户名
    private Set<String> getOnlineUsers(){
        return onlineUsers.keySet();
    }

    //接收客户端发送的数据时被调用
    @OnMessage public void onMessage(String message) {
        //将message 转换成message对象
        try {
            Message mess=JSON.parseObject(message, Message.class);
            System.out.println("mess: "+mess);
            System.out.println("服务端接受消息");
//            ObjectMapper mapper = new ObjectMapper();
//            Message mess = mapper.readValue(message, Message.class);
            System.out.println(mess);
            //获取要将数据发送的用户
            List<String> toNames = mess.getToUsernames();
            String fromName=mess.getFromUser();
            //获取消息数据
            String data = mess.getMessage();
            //获取当前登入的用户
            for (String toName:toNames) {
                //获取推送给指定用户消息格式的数据
                String resultMessage = MessageUtils.getMessage(false, fromName,data,new Date(),toName);
                System.out.println("发送消息的格式");
                System.out.println(resultMessage);
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
        String message=MessageUtils.getMessage(true,username,"离线了",new Date(),getOnlineUsers());
        broadcastAllUsers(message);
        String username = (String) httpSession.getAttribute("user");
        if (username != null){
            onlineUsers.remove(username);
            //UserInterceptor.onLineUsers.remove(username);
        }
        httpSession.removeAttribute("user");
//        Map a = UserInterceptor.onLineUsers;
//        System.out.println(a);
        //String message=MessageUtils.getMessage(true,username,"更新消息",null,getOnlineUsers());
    }



}
