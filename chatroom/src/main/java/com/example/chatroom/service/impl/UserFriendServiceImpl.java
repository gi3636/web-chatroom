package com.example.chatroom.service.impl;

import com.example.chatroom.dao.UserFriendDao;
import com.example.chatroom.entity.UserFriend;
import com.example.chatroom.service.UserFriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class UserFriendServiceImpl implements UserFriendService {

    @Autowired
    UserFriendDao userFriendDao;
    @Override
    public List<UserFriend> findFriend(Integer userId) {
        return userFriendDao.findUserFriendsByUserId(userId);
    }
}
