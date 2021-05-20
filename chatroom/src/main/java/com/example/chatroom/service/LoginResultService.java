package com.example.chatroom.service;

import com.example.chatroom.dao.LoginResultDao;
import com.example.chatroom.entity.LoginResult;

public interface LoginResultService {

    LoginResult addAndFlush(LoginResult loginResult);
}
