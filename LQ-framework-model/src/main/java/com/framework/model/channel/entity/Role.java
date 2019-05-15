package com.framework.model.channel.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 */

@Data
public class Role implements Serializable {
    private Integer id;
    private String displayName; //角色展示名
    private Integer level;      //角色等级
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", level=" + level +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
