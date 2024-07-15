package com.sdpzhong.dev.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("users")
@ApiModel("UserModel")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    @ApiModelProperty("用户ID")
    @TableId(type = IdType.AUTO, value = "user_id")
    private Integer userId;

    @ApiModelProperty("uid")
    private String uid;

    @ApiModelProperty("用户名")
    private String accountName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "密码", hidden = true)
    @TableField(select = false)
    private String password;

    @ApiModelProperty("手机号")
    private String mobile = "";

    @ApiModelProperty("性别")
    private String gender;
}
