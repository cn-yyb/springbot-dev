package com.sdpzhong.dev.http;

import lombok.Getter;

/**
 * @Author: zhongqing
 * @Description: 自定义http状态码枚举
 * @Date: 2024-07-10 10:02
 **/
@Getter
public enum HttpReturnCode {
    RC_NORMAL(0, "ok"),
    RC_FAIL(1, "fail"),
    RC200(200, "ok"),
    RC400(400, "请求失败，参数错误，请检查后重试。"),
    RC401(401, "无效token"),
    RC404(404, "未找到您请求的资源。"),
    RC405(405, "请求方式错误，请检查后重试。"),
    RC500(500, "服务器错误，请稍后重试");

    // 自定义状态码
    private final int code;

    // 自定义描述
    private final String msg;

    HttpReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
