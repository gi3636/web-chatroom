package com.example.chatroom.controller;

import com.example.chatroom.dao.UserDao;
import com.example.chatroom.entity.GroupChat;
import com.example.chatroom.entity.User;
import com.example.chatroom.service.impl.GroupChatServiceImpl;
import com.example.chatroom.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class SignUpController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private GroupChatServiceImpl groupChatService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Boolean registerCheck(HttpServletRequest request, HttpServletResponse response){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String confirmPassword=request.getParameter("confirm_password");
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || !(password.equals(confirmPassword))){
            log.info("用户名：{}，密码：{}，确认密码：{}，资料不完整或密码不一致",username,password,confirmPassword);
            return false;
        }
        if (userService.findOne(username,password)!=null){
            //做一些业务处理
            log.info("用户名：{}，密码：{}，确认密码：{}，已存在用户",username,password,confirmPassword);
            return false;
        }
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        GroupChat groupChat=groupChatService.findOne(1);
        if (groupChat!=null){
            user.getGroupChatList().add(groupChat);
        }else {
            groupChat=new GroupChat();
           groupChat.setGroupName("测试群");
           user.getGroupChatList().add(groupChat);
        }
        log.info("Register,name:{},password:{}  成功", user.getUsername(), user.getPassword());
        userService.add(user);
        return true;
    }
}
