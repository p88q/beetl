package com.god.beetl.dao;

import com.god.beetl.model.entity.Menu;
import com.god.beetl.model.entity.Role;

import java.util.List;

public interface MenuDao {

    List<Menu> getRoleMenuByRoles(List<Role> roles);
}
