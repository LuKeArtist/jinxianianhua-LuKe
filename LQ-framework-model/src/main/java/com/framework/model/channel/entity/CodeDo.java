package com.framework.model.channel.entity;

/**
 * @authod liuqi
 * @date 2019/5/13 15:59
 */
public class CodeDo {

    private String code;
    private String phone;

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

    @Override
    public String toString() {
        return "CodeDo{" +
                "code='" + code + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
