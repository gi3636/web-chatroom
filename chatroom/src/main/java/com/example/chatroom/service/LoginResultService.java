package com.example.chatroom.service;

import com.example.chatroom.dao.LoginResultDao;
import com.example.chatroom.entity.LoginResult;

import java.util.Date;

public interface LoginResultService {

    LoginResult addAndFlush(LoginResult loginResult);

    Date findLatestLoginTime(Integer userId);
}
