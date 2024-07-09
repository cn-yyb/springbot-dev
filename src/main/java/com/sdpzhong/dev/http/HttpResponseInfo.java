package com.sdpzhong.dev.http;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class HttpResponseInfo<T> {

    private Integer errcode; //状态码

    private String errmsg; //提示信息

    private T data; //数据

    private long timestamp;//接口请求时间

    public HttpResponseInfo() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> HttpResponseInfo<T> success(T data) {
        HttpResponseInfo<T> responseInfo = new HttpResponseInfo<>();
        responseInfo.setErrcode(HttpReturnCode.RC_NORMAL.getCode())
                .setErrmsg(HttpReturnCode.RC_NORMAL.getMsg())
                .setData(data);
        return responseInfo;
    }

    public static <T> HttpResponseInfo<T> error(int code, String msg) {
        HttpResponseInfo<T> responseInfo = new HttpResponseInfo<>();
        responseInfo.setErrcode(code)
                .setErrmsg(msg)
                .setData(null);
        return responseInfo;
    }
}