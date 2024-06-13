package com.sdpzhong.dev.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 拦截controller返回值，封装后统一返回格式
 */

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //如果Controller返回String的话，SpringBoot不会帮我们自动封装而直接返回，因此我们需要手动转换成json。
        if (o instanceof String) {
            System.out.println("selectedContentType: " + selectedContentType);
            response.getHeaders().set("content-type", "application/json;charset=UTF-8");
            return objectMapper.writeValueAsString(HttpResponseInfo.success(o));
        }
        //如果返回的结果是R对象，即已经封装好的，直接返回即可。
        //如果不进行这个判断，后面进行全局异常处理时会出现错误
        if (o instanceof HttpResponseInfo) {
            return o;
        }
        return HttpResponseInfo.success(o);
    }
}
