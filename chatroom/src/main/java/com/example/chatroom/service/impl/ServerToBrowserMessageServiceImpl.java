package com.example.chatroom.service.impl;

import com.example.chatroom.dao.ServerToBrowserMessageDao;
import com.example.chatroom.entity.ServerToBrowserMessage;
import com.example.chatroom.service.ServerToBrowserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerToBrowserMessageServiceImpl implements ServerToBrowserMessageService {


    @Autowired
    ServerToBrowserMessageDao serverToBrowserMessageDao;

    @Override
    public Boolean add(ServerToBrowserMessage serverToBrowserMessage) {
        serverToBrowserMessageDao.save(serverToBrowserMessage);
        return true;
    }
}
