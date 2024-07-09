package com.sdpzhong.dev.http.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Objects;


@Component
@WebFilter("/*")
@Slf4j
public class HttpRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("HttpRequestFilter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // log.info("HttpRequestFilter doFilter, servletRequest:{}, servletResponse:{}", servletRequest,servletResponse);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {

            @Override
            public String getHeader(String name) {

                // 获取 authorization 字段中的token，并赋值给 satoken
                // 如果 satoken 存在则不处理
                if (Objects.equals(name, "satoken")) {

                    String authorization = super.getHeader("Authorization");
                    String satoken = super.getParameter("satoken");

                    if (satoken == null && authorization != null) {
                        // 解析 Authorization 中的 token 并作为 satoken 字段的返回值
                        return authorization.startsWith("Bearer ") ? authorization.substring(7) : authorization;
                    } else {
                        return super.getHeader(name);

                    }

                }

                return super.getHeader(name);
            }
        };


        // log.info("satoken: {}", requestWrapper.getHeader("satoken"));

        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("HttpRequestFilter destroy");
        Filter.super.destroy();
    }
}
