package com.framework.model.channel.entity;

import lombok.Data;

import java.util.Date;

/**
 * @authod liuqi
 * @date 2019/5/13 15:59
 */

@Data
public class CodeDo {

    private String code;
    private String phone;
    private Integer id;
    private Date created_at;
    private Date updated_at;



    @Override
    public String toString() {
        return "CodeDo{" +
                "code='" + code + '\'' +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
