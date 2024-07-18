package com.sdpzhong.dev.common;

import lombok.Getter;

/**
 * @Author: zhongqing
 * @Description: 文章状态枚举
 * @Date: 2024-07-18 14:12
 **/

@Getter
public enum ArticleStatus {

    SKETCH(0, "草稿"),
    PENDING_REVIEW(1, "待审核"),
    REVIEW_FAIL(2, "审核失败"),
    MANUAL_REVIEW(3, "人工审核"),
    MANUAL_REVIEW_FAIL(4, "人工审核失败"),
    APPROVED(5, "审核通过"),
    PENDING_PUBLISH(6, "待发布"),
    PUBLISHED(7, "己发布"),
    BANNED(8, "已封禁"),
    //
    ;

    private int status;

    private String desc;

    ArticleStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
