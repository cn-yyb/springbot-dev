package com.sdpzhong.dev.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "UserLoginResponseVo")
public class UserLoginResponseVo implements Serializable {

    @Schema(name = "登录凭证")
    private String token;

    @Schema(name = " 剩余有效期（秒）")
    private long timeout;

}
