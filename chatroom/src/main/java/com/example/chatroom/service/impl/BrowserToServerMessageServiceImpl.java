package com.example.chatroom.service.impl;

import com.example.chatroom.dao.BrowserToServerMessageDao;
import com.example.chatroom.entity.BrowserToServerMessage;
import com.example.chatroom.service.BrowserToServerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrowserToServerMessageServiceImpl implements BrowserToServerMessageService {

    @Autowired
    BrowserToServerMessageDao browserToServerMessageDao;

    @Override
    public Boolean add(BrowserToServerMessage browserToServerMessage) {
        browserToServerMessageDao.save(browserToServerMessage);
        return true;
    }
}
