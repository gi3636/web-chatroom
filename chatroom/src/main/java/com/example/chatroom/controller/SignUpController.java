package com.example.chatroom.controller;

import com.example.chatroom.dao.UserDao;
import com.example.chatroom.entity.User;
import com.example.chatroom.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignUpController {

    @Autowired
    private UserServiceImpl userService;

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
            System.out.println("用户名:"+username+" 密码:"+password+" 确认密码:"+confirmPassword);
            System.out.println("资料不完整或密码不一致");
            return false;
        }
        if (userService.findOne(username,password)!=null){
            //做一些业务处理
            return false;
        }
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.addAndFlush(user);
        return true;
    }
}
