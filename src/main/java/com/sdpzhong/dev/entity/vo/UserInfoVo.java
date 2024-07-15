package com.sdpzhong.dev.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "UserInfoVo")
public class UserInfoVo implements Serializable {
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "UID")
    private String uid;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "手机号")
    private String mobile;
}
