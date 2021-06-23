package com.example.chatroom.controller;

import com.example.chatroom.entity.GroupChat;
import com.example.chatroom.entity.ServerToBrowserMessage;
import com.example.chatroom.entity.User;
import com.example.chatroom.service.impl.GroupChatServiceImpl;
import com.example.chatroom.service.impl.UserServiceImpl;
import com.example.chatroom.ws.ChatEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;



@Controller
public class JsonController {

    @Autowired
    GroupChatServiceImpl groupChatService;

    @Autowired
    UserServiceImpl userService;

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
    @RequestMapping("/getOnlineUsers")
    @ResponseBody
    public String getOnlineUsers(HttpSession httpSession){
        String onlineUsers = (String) httpSession.getAttribute("onlineUsers");
        return onlineUsers;
    }

    @RequestMapping("/getGroupChatUserList/{groupChatId}")
    @ResponseBody
    public List<User> getGroupChat(@PathVariable String groupChatId){
        return groupChatService.findOne(Integer.parseInt(groupChatId)).getGroupChatUsers();
    }


    @RequestMapping("/getGroupChat/{id}")
    @ResponseBody
    public GroupChat getGroupChat(@PathVariable Integer id){
        return groupChatService.findOne(id);
    }

    @RequestMapping("/getUser/{id}")
    @ResponseBody
    public User getUser(@PathVariable Integer id){
        System.out.println("调用getUser");
        return userService.findOne(id);
    }

    @RequestMapping("/getUser/{fromUserId}/{toUserId}")
    public ServerToBrowserMessage getHistoryMessage(@PathVariable Integer fromUserId,@PathVariable Integer toUserId){
        return null;
    }
}
