package com.sdpzhong.dev.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@ResponseBody
public class RestExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param e BusinessException
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public HttpResponseInfo<String> businessException(BusinessException e) {
        log.error("业务异常 code={}, BusinessException = {}", e.getCode(), e.getMessage(), e);
        return HttpResponseInfo.error(e.getCode(), e.getMsg());
    }

    /**
     * 处理404异常
     *
     * @param e NoHandlerFoundException
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponseInfo<String> noHandlerFoundException(HttpServletRequest req, Exception e) {
        log.error("404异常 NoHandlerFoundException, method = {}, path = {} ", req.getMethod(), req.getServletPath(), e);
        return HttpResponseInfo.error(HttpReturnCode.RC404.getCode(), HttpReturnCode.RC404.getMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param e NullPointerException
     * @return
     * @description 空指针异常定义为前端传参错误，返回400
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponseInfo<String> nullPointerException(NullPointerException e) {
        log.error("空指针异常 NullPointerException ", e);
        return HttpResponseInfo.error(HttpReturnCode.RC400.getCode(), HttpReturnCode.RC400.getMsg());
    }

    /**
     * 处理其他异常
     *
     * @param e otherException
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponseInfo<String> exception(Exception e) {
        log.error("未知异常 exception = {}", e.getMessage(), e);
        //  HttpReturnCode.RC500.getMsg()
        return HttpResponseInfo.error(HttpReturnCode.RC500.getCode(), e.getMessage());
    }
}
