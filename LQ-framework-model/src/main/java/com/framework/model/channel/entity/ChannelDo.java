package com.framework.model.channel.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户表
 * @authod liuqi
 * @date 2019/5/13 11:49
 */

@Data
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
    private String token;
    private Integer WxStatus; //微信认证状态


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
                ", nickname='" + nickname + '\'' +
                ", token='" + token + '\'' +
                ", WxStatus=" + WxStatus +
                '}';
    }
}
