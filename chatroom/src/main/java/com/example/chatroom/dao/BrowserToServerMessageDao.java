package com.example.chatroom.dao;

import com.example.chatroom.entity.BrowserToServerMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrowserToServerMessageDao extends JpaRepository<BrowserToServerMessage,Integer> {

}
