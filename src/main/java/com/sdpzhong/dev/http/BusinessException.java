package com.sdpzhong.dev.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
