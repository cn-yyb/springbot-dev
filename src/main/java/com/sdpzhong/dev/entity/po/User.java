package com.sdpzhong.dev.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("users")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("UserModel")
public class User implements Serializable {
    @ApiModelProperty("用户ID")
    @TableId(type = IdType.AUTO, value = "user_id")
    private Integer userId;

    @ApiModelProperty("uid")
    private String uid;

    @ApiModelProperty("用户名")
    private String accountName;
    
    //    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty("密码")
    private String password;

    private String mobile = "";

    private String gender;

    private String role;
}
