package com.example.chatroom.service.impl;

import com.example.chatroom.dao.GroupChatDao;
import com.example.chatroom.entity.GroupChat;
import com.example.chatroom.service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupChatServiceImpl implements GroupChatService {
    @Autowired
    private GroupChatDao groupChatDao;

    @Override
    public Boolean addAndFlush(GroupChat groupChat) {
        groupChatDao.saveAndFlush(groupChat);
        return true;
    }

    @Override
    public Boolean delete(GroupChat groupChat) {
        groupChatDao.delete(groupChat);
        return true;
    }

    @Override
    public GroupChat findOne(Integer groudChatId) {
        return groupChatDao.findGroupChatByGroupChatId(groudChatId);
    }


}
