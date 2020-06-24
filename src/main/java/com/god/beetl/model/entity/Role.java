package com.god.beetl.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色
 */
@Data
@Accessors(chain = true)
public class Role extends BaseEntity {
    // 角色名称
    private String roleName;
}
