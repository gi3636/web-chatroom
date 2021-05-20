package com.example.chatroom.controller;

import com.example.chatroom.entity.LoginResult;
import com.example.chatroom.entity.User;
import com.example.chatroom.service.impl.LoginResultServiceImpl;
import com.example.chatroom.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    LoginResultServiceImpl loginResultService;



    /**
     * 初次登入
     * @return
     */
    @RequestMapping(value = "/",method =RequestMethod.GET)
    public String firstLogin(){
        return "login";
    }

    /**
     * 跳转登录界面
     * @return
     */
    @RequestMapping(value = "/login",method =RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 跳转去首页
     * @return
     */
    @RequestMapping(value = "/chatroom",method = RequestMethod.GET)
    public String index(){
        return "chatroom";
    }


    /**
     * 对login进行检查

     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public LoginResult loginCheck(HttpServletRequest request, Model model){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=userService.findOne(username,password);
        LoginResult loginResult=new LoginResult();
        if (user!=null)
        {
            loginResult.setUserId(user.getUserId());
            loginResult.setLoginMessage("登入成功");
            loginResult.setResult(true);
            loginResultService.addAndFlush(loginResult);
            System.out.println(user);
            System.out.println("登入成功");
        }else {
            loginResult.setUserId(null);
            loginResult.setLoginMessage("登入失败");
            loginResult.setResult(false);
            System.out.println("登入失败");
            model.addAttribute("msg","密码或账号错误");
        }
        System.out.println(loginResult);
        return loginResult;
    }

}
