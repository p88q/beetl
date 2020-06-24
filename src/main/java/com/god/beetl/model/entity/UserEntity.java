package com.god.beetl.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class UserEntity implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private List<Role> userRoles;
    private List<Menu> roleMenus;

    private Collection<? extends GrantedAuthority> authorities;

    public UserEntity(){}

    public UserEntity(String username, String password, List<Role> userRoles, List<Menu> roleMenus, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
        this.roleMenus = roleMenus;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Menu> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(List<Menu> roleMenus) {
        this.roleMenus = roleMenus;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
