package com.sdpzhong.dev.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName contact
 */
@TableName(value = "contact")
@Data
public class Contact implements Serializable {
    /**
     * 联系人编号
     */
    @TableId(type = IdType.AUTO, value = "contact_id")
    private Integer contactId;

    /**
     *
     */
    private String uid;

    /**
     *
     */
    private Integer groupId;

    /**
     *
     */
    private Integer chatId;

    /**
     *
     */
    private String friendUid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类型 0-私聊 | 1-群聊
     */
    private Integer type;

    /**
     *
     */
    private Date createdAt;

    /**
     *
     */
    private Date updatedAt;

    /**
     *
     */
    private Date deletedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}