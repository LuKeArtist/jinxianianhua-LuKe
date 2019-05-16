package com.framework.model.channel.vo;

import lombok.Data;

/**
 * @Description: jinxianianhua
 * @EnglishName LuKe
 * @authod liuqi
 * @date 2019/5/16 9:57
 */

@Data
public class ChannelCodeVo {

    private String phone;
    private String password;
    private String code;
    private String username;


    @Override
    public String toString() {
        return "ChannelCodeVo{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
