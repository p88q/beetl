package com.god.beetl.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单
 */
@Data
@Accessors(chain = true)
public class Menu extends BaseEntity{
    // 菜单名称
    private String menuName;
    // Controller路径
    private String menuUrl;
    // 菜单编码
    private String menuCode;
    // 父菜单id
    private Long parentId;
    // 菜单类型: 0-菜单 1-按钮
    private Integer menuType;
    // 显示序号
    private Integer orderNum;
}
