package com.example.chatroom.service;

import com.example.chatroom.entity.BrowserToServerMessage;
import org.springframework.stereotype.Service;

@Service
public interface BrowserToServerMessageService {

    public Boolean add(BrowserToServerMessage browserToServerMessage);
}
