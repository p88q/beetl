package com.god.beetl.dao;

import com.god.beetl.model.entity.UserEntity;

public interface UserDao {

    UserEntity getUserByUsername(String username);

    int saveUser(UserEntity entity);
}
