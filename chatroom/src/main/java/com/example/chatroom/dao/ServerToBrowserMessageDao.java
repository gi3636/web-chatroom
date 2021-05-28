package com.example.chatroom.dao;

import com.example.chatroom.entity.ServerToBrowserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerToBrowserMessageDao extends JpaRepository<ServerToBrowserMessage,Integer> {
}
