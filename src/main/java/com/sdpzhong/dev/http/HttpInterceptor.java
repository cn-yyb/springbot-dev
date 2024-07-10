package com.sdpzhong.dev.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author: zhongqing
 * @Description: HttpInterceptor 拦截器
 * @Date: 2024-07-10 10:00
 **/
@Slf4j
@Component
public class HttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求处理之前进行调用（Controller方法调用之前）
        // log.info("preHandle start, request:{}", request.getHeader("Authorization"));
        return true; // 如果返回false，则停止流程，api不会被调用
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // log.info("preHandle end, response:{}", response);
        // 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
        // 可篡改返回值
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在整个请求结束之后调用，也就是在DispatcherServlet渲染了视图执行
        // log.info("afterCompletion start, response:{}", response);
    }
}
