package com.example.chatroom.dao;

import com.example.chatroom.entity.LoginResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginResultDao extends JpaRepository<LoginResult,Integer> {
}
