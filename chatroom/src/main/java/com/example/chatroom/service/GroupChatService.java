package com.example.chatroom.service;

import com.example.chatroom.dao.GroupChatDao;
import com.example.chatroom.entity.GroupChat;
import com.example.chatroom.entity.LoginResult;
import org.springframework.stereotype.Service;

@Service
public interface GroupChatService {

  public Boolean addAndFlush(GroupChat groupChat);
  public Boolean delete(GroupChat groupChat);
  public GroupChat findOne(Integer groudChatId);
}
