package com.god.beetl.service.impl;

import com.god.beetl.dao.UserDao;
import com.god.beetl.model.entity.UserEntity;
import com.god.beetl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(UserEntity entity) {
        userDao.saveUser(entity);
    }
}
