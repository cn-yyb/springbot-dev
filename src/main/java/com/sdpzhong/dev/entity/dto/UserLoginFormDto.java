package com.sdpzhong.dev.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginFormDto implements Serializable {
    /*
     *  用户名
     * */
    private String username;

    /*
     * 密码（MD5 加密）
     * */
    private String password;

}
