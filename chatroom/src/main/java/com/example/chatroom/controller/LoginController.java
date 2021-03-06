package com.example.chatroom.controller;

import com.example.chatroom.entity.LoginResult;
import com.example.chatroom.entity.User;
import com.example.chatroom.service.impl.LoginResultServiceImpl;
import com.example.chatroom.service.impl.UserServiceImpl;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

@Controller
@Slf4j
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
    public LoginResult loginCheck(HttpSession httpSession,HttpServletRequest request, Model model) throws UnknownHostException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=userService.findOne(username,password);
        LoginResult loginResult=new LoginResult();
        if (user!=null)
        {
            Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();//获取浏览器
            Version version = browser.getVersion(request.getHeader("User-Agent"));
            String info = browser.getName() + "/" + version.getVersion();
            String ip= InetAddress.getLocalHost().getHostAddress();
            loginResult.setUserId(user.getUserId());
            loginResult.setLoginMessage("登入成功");
            loginResult.setResult(true);
            loginResult.setBrowser(info);
            loginResult.setIpAddr(ip);
            httpSession.setAttribute("user",user);
            httpSession.setAttribute("userId", user.getUserId());
            httpSession.setAttribute("username", user.getUsername());
            loginResultService.addAndFlush(loginResult);
            log.info("{}登入成功",user.getUsername());
        }else {
            log.info("账号：{}，密码：{}，登入失败",username,password);
            loginResult.setUserId(null);
            loginResult.setLoginMessage("登入失败");
            loginResult.setResult(false);
        }
        return loginResult;
    }



}
