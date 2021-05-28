package com.example.chatroom.controller;

import com.example.chatroom.entity.GroupChat;
import com.example.chatroom.service.impl.GroupChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GroupChatController {
    @Autowired
    private GroupChatServiceImpl groupChatService;

    @RequestMapping("/getGroupChat/{id}")
    @ResponseBody
    public GroupChat getGroupChat(@PathVariable Integer id){
        return groupChatService.findOne(id);
    }
}
