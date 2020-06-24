package com.god.beetl.config;

import com.god.beetl.model.entity.Menu;
import com.god.beetl.model.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * RBAC数据模型控制权限
 */
@Component("rbacPermission")
public class RbacPermission {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserEntity) {
            // 读取用户所有权限菜单
            List<Menu> menus = ((UserEntity) principal).getRoleMenus();
            for (Menu menu : menus) {
                if (antPathMatcher.match(menu.getMenuUrl(), request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;

    }
}
