package com.example.chatroom.config;


import com.example.chatroom.service.GroupChatService;
import com.example.chatroom.service.LoginResultService;
import com.example.chatroom.service.impl.BrowserToServerMessageServiceImpl;
import com.example.chatroom.service.impl.GroupChatServiceImpl;
import com.example.chatroom.service.impl.LoginResultServiceImpl;
import com.example.chatroom.service.impl.ServerToBrowserMessageServiceImpl;
import com.example.chatroom.ws.ChatEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Bean
    //注入ServerEndPointExporter bean对象,自动注册使用了@ServerEndPoint注解的bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Autowired
    public void loginResultService(LoginResultServiceImpl loginResultService){
        ChatEndPoint.loginResultService=loginResultService;
    }
    @Autowired
    public void groupChatService(GroupChatServiceImpl groupChatService){
        ChatEndPoint.groupChatService=groupChatService;
    }

    @Autowired
    public void browserToServerMessageService(BrowserToServerMessageServiceImpl browserToServerMessageService)
    {
        ChatEndPoint.browserToServerMessageService=browserToServerMessageService;
    }

    @Autowired
    public void serverToBrowserMessageService(ServerToBrowserMessageServiceImpl serverToBrowserMessageService)
    {
        ChatEndPoint.serverToBrowserMessageService=serverToBrowserMessageService;
    }

}
