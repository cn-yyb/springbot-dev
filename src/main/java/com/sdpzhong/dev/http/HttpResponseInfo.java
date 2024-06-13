package com.sdpzhong.dev.http;

import lombok.Data;


@Data
public class HttpResponseInfo<T> {

    private Integer code; //状态码

    private String msg; //提示信息

    private T data; //数据

    private long timestamp;//接口请求时间

    public HttpResponseInfo() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> HttpResponseInfo<T> success(T data) {
        HttpResponseInfo<T> r = new HttpResponseInfo<>();
        r.setCode(HttpReturnCode.RC200.getCode());
        r.setMsg(HttpReturnCode.RC200.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> HttpResponseInfo<T> error(int code, String msg) {
        HttpResponseInfo<T> r = new HttpResponseInfo<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }
}