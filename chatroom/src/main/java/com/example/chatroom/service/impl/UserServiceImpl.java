package com.example.chatroom.service.impl;

import com.example.chatroom.dao.UserDao;
import com.example.chatroom.entity.User;
import com.example.chatroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User findOne(String username,String password) {
        User result=userDao.findUserByUsernameAndPassword(username,password);
        return result;
    }


    @Override
    public User addAndFlush(User user) {
        return  userDao.saveAndFlush(user);
    }

    @Override
    public User add(User user)
    {
        return userDao.save(user);
    }

    @Override
    public Boolean delete(User user) {
        userDao.delete(user);
        userDao.flush();
        return true;
    }
}
