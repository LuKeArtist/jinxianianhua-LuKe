package com.framework.model.channel.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限
 */

@Data
public class Permission implements Serializable {

    private Integer id;
    private String name;        //权限唯一ID
    private Integer parentId;   //父类ID,根节点为0
    private String displayName;     //权限展示名
    private Integer isShow;    //是否展示在菜单栏
    private Integer sort;       //排序
    private Integer status;     //状态 0：正常 1：禁用
    private Date createdAt;
    private Date updatedAt;


}
