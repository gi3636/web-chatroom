package com.example.chatroom.service;


import com.example.chatroom.entity.UserFriend;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserFriendService {

    public List<UserFriend> findFriend(Integer userId);
}
