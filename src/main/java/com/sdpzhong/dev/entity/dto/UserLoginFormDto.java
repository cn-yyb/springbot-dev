package com.sdpzhong.dev.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Schema(name = "UserLoginFormDto")
@Data
public class UserLoginFormDto implements Serializable {
    /*
     *  用户名
     * */
    @Schema(description = "用户名")
    @NotBlank
    private String username;

    /*
     * 密码（MD5 加密）
     * */
    @Schema(description = "密码（需进行MD5加密）")
    @NotBlank
    private String password;

}
