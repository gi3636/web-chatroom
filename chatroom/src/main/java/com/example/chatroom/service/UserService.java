package com.example.chatroom.service;

import com.example.chatroom.entity.GroupChat;
import com.example.chatroom.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User findOne(String username,String password);
    public User findOne(String username);
    public User findOne(Integer userId);
    public int changeAvatarPath(String path,Integer userId);
    public User addGroupChat(GroupChat groupChat);




    public User addAndFlush(User user);

    public User add(User user);

    public Boolean delete(User user);
}
