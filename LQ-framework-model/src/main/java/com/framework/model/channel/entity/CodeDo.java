package com.framework.model.channel.entity;

import java.util.Date;

/**
 * @authod liuqi
 * @date 2019/5/13 15:59
 */
public class CodeDo {

    private String code;
    private String phone;
    private Integer id;
    private Date created_at;
    private Date updated_at;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }


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
