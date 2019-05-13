package com.framework.model.channel.entity;

import java.util.Date;

/**
 * @authod liuqi
 * @date 2019/5/13 11:49
 */
public class ChannelDo {

    private Integer id;
    private String username;
    private String phone;
    private String password;
    private Integer status;
    private Date login_at;
    private Date created_at;
    private Date updated_at;
    private String last_login_token;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLogin_at() {
        return login_at;
    }

    public void setLogin_at(Date login_at) {
        this.login_at = login_at;
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

    public String getLast_login_token() {
        return last_login_token;
    }

    public void setLast_login_token(String last_login_token) {
        this.last_login_token = last_login_token;
    }

    @Override
    public String toString() {
        return "ChannelDo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", login_at=" + login_at +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", last_login_token='" + last_login_token + '\'' +
                '}';
    }
}
