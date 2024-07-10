package com.sdpzhong.dev.http;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: zhongqing
 * @Description: 自定义异常
 * @Date: 2024-07-10 10:01
 **/

@Getter
@Setter
@Accessors(chain = true)
public class BusinessException extends RuntimeException {
    private int code;
    private String msg;

    public BusinessException() {
    }

    public BusinessException(HttpReturnCode returnCode) {
        this(returnCode.getCode(), returnCode.getMsg());
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
