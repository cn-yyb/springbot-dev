package com.sdpzhong.dev.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserLoginFormResponse implements Serializable {

    private String token;
    private String expireTime;
}
