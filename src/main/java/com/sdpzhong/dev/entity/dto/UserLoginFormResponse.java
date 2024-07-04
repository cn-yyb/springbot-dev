package com.sdpzhong.dev.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserLoginFormResponse implements Serializable {

    // 登录凭证
    private String token;

    // 剩余有效期（秒）
    private long tokenTimeout;
}
