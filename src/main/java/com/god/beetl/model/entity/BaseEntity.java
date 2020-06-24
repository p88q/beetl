package com.god.beetl.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 基础类，仅共有属性
 */
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1;
    // id
    protected Long id;
    // 状态
    protected Integer state;
    // 创建人
    protected String creator;
    // 创建时间
    protected String createTime;
    // 修改人
    protected String updator;
    // 修改时间
    protected String updateTime;
    // 删除标记(默认不删除)
    protected Boolean deleted = false;
}
