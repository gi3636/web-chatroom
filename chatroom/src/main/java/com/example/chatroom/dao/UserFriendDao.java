package com.example.chatroom.dao;

import com.example.chatroom.entity.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFriendDao  extends JpaRepository<UserFriend,Integer> {
    //寻找好友
     List<UserFriend> findUserFriendsByUserId(Integer userId);
}
