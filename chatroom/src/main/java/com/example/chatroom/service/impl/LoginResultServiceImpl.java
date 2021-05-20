package com.example.chatroom.service.impl;

import com.example.chatroom.dao.LoginResultDao;
import com.example.chatroom.entity.LoginResult;
import com.example.chatroom.service.LoginResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginResultServiceImpl implements LoginResultService {

    @Autowired
    LoginResultDao loginResultDao;

    @Override
    public LoginResult addAndFlush(LoginResult loginResult) {
      return loginResultDao.saveAndFlush(loginResult);
    }

}
