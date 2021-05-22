package com.example.chatroom.dao;

import com.example.chatroom.entity.LoginResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoginResultDao extends JpaRepository<LoginResult,Integer> {

    /*查询最新登入时间*/
    @Query("select a.loginTime from LoginResult a where a.userId =?1")
    List<Date> findByUserId(Integer userId);
}
