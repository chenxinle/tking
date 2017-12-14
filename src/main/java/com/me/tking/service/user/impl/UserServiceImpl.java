package com.me.tking.service.user.impl;

import com.me.tking.dao.UserDao;
import com.me.tking.entity.User;
import com.me.tking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }
}
