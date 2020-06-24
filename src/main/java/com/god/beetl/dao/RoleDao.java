package com.god.beetl.dao;

import com.god.beetl.model.entity.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getUserRoleByUserId(Long userId);
}
