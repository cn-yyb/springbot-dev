package com.sdpzhong.dev.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Schema(name = "UserRegisterFormDto")
@Data
public class UserRegisterFormDto implements Serializable {

    @Schema(description = "用户名")
    @NotBlank
    @Size(min = 6, max = 20)
    public String username;

    @Schema(description = "密码")
    @NotBlank
    // @Pattern(regexp = "")
    public String password;

    @Schema(description = "邮箱")
    @NotBlank
    @Email
    public String email;
}
