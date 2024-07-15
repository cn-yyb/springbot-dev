package com.sdpzhong.dev.http;

import com.sdpzhong.dev.common.HttpReturnCode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: zhongqing
 * @Description: 全局统一接口返回格式实体
 * @Date: 2024-07-10 10:00
 **/

@Data
@Accessors(chain = true)
public class HttpResponseInfo<T> {

    private Integer errcode; // 状态码

    private String errmsg; // 提示信息

    private T data; // 数据

    private long timestamp;// 接口请求时间

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

    // 自定义 HttpReturnCode
    public static <T> HttpResponseInfo<T> success(T data, HttpReturnCode returnCode) {
        HttpResponseInfo<T> responseInfo = new HttpResponseInfo<>();
        responseInfo.setErrcode(returnCode.getCode())
                .setErrmsg(returnCode.getMsg())
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