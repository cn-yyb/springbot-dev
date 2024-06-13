package com.sdpzhong.dev.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    @TableId(type = IdType.AUTO, value = "user_id")
    private Integer userId;
    private String uid;
    private String accountName;
    //    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;
    private String mobile = "";
    private String gender;
    private String role;
}
