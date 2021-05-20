package com.example.chatroom.service;

import com.example.chatroom.entity.User;

public interface UserService {
    public User findOne(String username,String password);


    User addAndFlush(User user);

    User add(User user);

    Boolean delete(User user);
}
