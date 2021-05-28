package com.example.chatroom.controller;

import com.example.chatroom.entity.ServerToBrowserMessage;
import com.example.chatroom.service.ServerToBrowserMessageService;
import com.example.chatroom.service.impl.BrowserToServerMessageServiceImpl;
import com.example.chatroom.service.impl.ServerToBrowserMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MessageController {
    @Autowired
    ServerToBrowserMessageServiceImpl serverToBrowserMessageService;
    @Autowired
    BrowserToServerMessageServiceImpl browserToServerMessageService;

    @RequestMapping("/getServerToBrowserMessage/{username}")
    @ResponseBody
    public List<ServerToBrowserMessage> getServerToBrowserMessage(@PathVariable String username){
        
        return null;

    }


}
