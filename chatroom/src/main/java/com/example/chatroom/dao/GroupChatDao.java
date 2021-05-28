package com.example.chatroom.dao;

import com.example.chatroom.entity.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChatDao extends JpaRepository<GroupChat,Integer> {

   GroupChat findGroupChatByGroupChatId(Integer groupChatId);
}
