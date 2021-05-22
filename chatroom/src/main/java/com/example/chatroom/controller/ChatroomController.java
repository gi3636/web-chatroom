package com.example.chatroom.controller;

import com.example.chatroom.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class ChatroomController {

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return user;
    }
    @RequestMapping("/getUsername")
    @ResponseBody
    public String getUsername(HttpSession httpSession){
        String username = (String) httpSession.getAttribute("username");
        return username;
    }
}
