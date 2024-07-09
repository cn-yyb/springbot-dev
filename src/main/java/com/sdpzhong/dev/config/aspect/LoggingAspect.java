package com.sdpzhong.dev.config.aspect;

import com.sdpzhong.dev.entity.RequestLogEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @PostConstruct
    public void init() {
        log.info("LoggingAspect init");
    }

    @Pointcut("execution(public * com.sdpzhong.dev.controller..*.*(..)))")
    public void servicePointCut() {
    }

    @Around(value = "servicePointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // 请求参数
        Object[] args = joinPoint.getArgs();
        // 响应结果
        Object result = null;

        RequestLogEntity requestLogEntity = new RequestLogEntity();

        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
//            log.error(throwable.getMessage(), throwable);
            requestLogEntity.setResult(throwable.getMessage());
            throw throwable;
        } finally {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                HttpServletResponse response = attributes.getResponse();

                String requestURI = request.getRequestURI();
                String method = request.getMethod();
                String queryString = request.getQueryString();
                Object statusCode = null;
                String ip = request.getRemoteAddr();
                String token = request.getHeader("satoken");

                Object[] argument = joinPoint.getArgs();


                if (response != null) {
                    statusCode = response.getStatus();
                }
                
                requestLogEntity
                        .setToken(token)
                        .setUrl(requestURI)
                        .setMethod(method)
                        .setIp(ip)
                        .setQueryString(queryString)
                        .setStatusCode(statusCode)
                        .setArguments(argument);

                if (requestLogEntity.result != null) {
                    requestLogEntity.setResult(result);
                }

                // 打印日志
                requestLogEntity.consoleLog();
            }
        }
        // 可篡改返回值
        return result;
    }
}
