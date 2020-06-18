package com.god.beetl.model.entity;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String roleName;


    @Override
    public String getAuthority() {
        return null;
    }
}
