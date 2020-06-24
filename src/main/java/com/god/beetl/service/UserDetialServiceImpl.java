package com.god.beetl.service;

import com.god.beetl.dao.MenuDao;
import com.god.beetl.dao.RoleDao;
import com.god.beetl.dao.UserDao;
import com.god.beetl.model.entity.Menu;
import com.god.beetl.model.entity.Role;
import com.god.beetl.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class UserDetialServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDao.getUserByUsername(username);
        if (user != null) {
            List<Role> roles = roleDao.getUserRoleByUserId(user.getId());
            Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            List<Menu> menus = menuDao.getRoleMenuByRoles(roles);
            return new UserEntity(username, user.getPassword(), roles, menus, authorities);
        } else {
            System.out.println("用户不存在!");
            throw new RuntimeException("用户不存在");
        }
    }
}
