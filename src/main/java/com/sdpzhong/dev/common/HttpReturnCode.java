package com.sdpzhong.dev.common;

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

    // Http Code
    RC200(200, "success"),
    RC400(400, "请求失败，参数错误，请检查后重试。"),
    RC401(401, "登录凭证（Token）已过期"),
    RC403(403, "服务器拒绝访问"),
    RC404(404, "未找到您请求的资源"),
    RC405(405, "请求方式错误，请检查后重试"),
    RC500(500, "服务器错误，请稍后重试"),

    // 业务状态码
    RC_NO_REGISTERED(1001, "账号未注册"),
    RC_WRONG_PASSWORD(1002, "密码错误"),
    RC_LOGIN_REJECTED(1003, "账号或密码错误"),
    RC_ACCOUNT_REJECTED(1004, "账号状态异常"),
    RC_ACCOUNT_REGISTERED(1005, "该用户名已经注册"),

    // ..
    ;

    // 状态码
    private final int code;

    // 自定义描述
    private final String msg;

    HttpReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
