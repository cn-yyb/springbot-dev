package com.sdpzhong.dev.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

/**
 * 拦截controller返回值，封装后统一返回格式
 */

@RestControllerAdvice(basePackages = {"com.sdpzhong.dev.controller"})
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object result, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (result instanceof String) {
            response.getHeaders().set("content-type", "application/json;charset=UTF-8");
            return objectMapper.writeValueAsString(HttpResponseInfo.success(result));
        }

        if (selectedContentType.isCompatibleWith(MediaType.APPLICATION_JSON)) {
            
            if (result instanceof HttpResponseInfo) {
                return result;
            }

            return HttpResponseInfo.success(result);
        } else {
            return result;
        }


    }
}
