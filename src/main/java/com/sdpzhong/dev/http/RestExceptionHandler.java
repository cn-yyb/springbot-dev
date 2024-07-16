package com.sdpzhong.dev.http;

import cn.dev33.satoken.exception.NotLoginException;
import com.sdpzhong.dev.common.HttpReturnCode;
import com.sdpzhong.dev.utils.LogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: zhongqing
 * @Description: 全局异常捕获处理
 * @Date: 2024-07-08 16:59
 **/

@Slf4j
@RestControllerAdvice
@ResponseBody
public class RestExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param e BusinessException
     * @return HttpResponseInfo<String>
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseInfo<String> businessException(BusinessException e) {
        // log.error("业务异常 code={}, BusinessException = {}", e.getCode(), e.getMessage(), e);
        Map<String, Object> map = new TreeMap<>();
        map.put("code", e.getCode());
        map.put("msg", e.getMessage());
        map.put("exception", e);
        log.error(LogTemplate.createLogTemplate(map));
        return HttpResponseInfo.error(e.getCode(), e.getMsg());
    }

    /**
     * 处理404异常
     *
     * @param e NoHandlerFoundException
     * @return HttpResponseInfo<String>
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponseInfo<String> noHandlerFoundException(HttpServletRequest req, Exception e) {
        Map<String, Object> map = new TreeMap<>();
        map.put("path", req.getRequestURI());
        map.put("msg", "404 NoHandlerFoundException " + e.getMessage());
        map.put("exception", e);
        log.error(LogTemplate.createLogTemplate(map));
        return HttpResponseInfo.error(HttpReturnCode.RC404.getCode(), HttpReturnCode.RC404.getMsg());
    }

    /**
     * 空指针异常
     *
     * @param e NullPointerException
     * @return HttpResponseInfo<String>
     * @description 空指针异常定义为前端传参错误，返回400
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponseInfo<String> nullPointerException(NullPointerException e) {
        Map<String, Object> map = new TreeMap<>();
        map.put("msg", "NullPointerException " + e.getMessage());
        map.put("exception", e);
        log.error(LogTemplate.createLogTemplate(map));
        return HttpResponseInfo.error(HttpReturnCode.RC400.getCode(), HttpReturnCode.RC400.getMsg());
    }


    /**
     * 参数校验错误
     *
     * @param e MethodArgumentNotValidException
     * @return HttpResponseInfo<String>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponseInfo<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        String errorMsg = combineErrors(e);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "MethodArgumentNotValidException " + errorMsg);
        map.put("exception", e);
        log.error(LogTemplate.createLogTemplate(map));

        return HttpResponseInfo.error(HttpReturnCode.RC400.getCode(), errorMsg);
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public HttpResponseInfo<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        Map<String, Object> map = new TreeMap<>();
        map.put("msg", "请求方法不支持 HttpRequestMethodNotSupportedException " + e.getMessage());
        map.put("exception", e);
        log.error(LogTemplate.createLogTemplate(map));
        return HttpResponseInfo.error(HttpReturnCode.RC405.getCode(), HttpReturnCode.RC405.getMsg());
    }

    /**
     * 空指针异常
     *
     * @param e SaTokenException
     * @return HttpResponseInfo<String>
     * @description 401
     */
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public HttpResponseInfo<String> unAuthorizationException(NotLoginException e) {
        // log.error("未授权访问 UnAuthorizationException ", e);
        Map<String, Object> map = new TreeMap<>();
        map.put("code", e.getCode());
        map.put("msg", "未授权访问 UnAuthorizationException " + e.getMessage());
        map.put("exception", e);
        log.error(LogTemplate.createLogTemplate(map));
        return HttpResponseInfo.error(HttpReturnCode.RC401.getCode(), HttpReturnCode.RC401.getMsg());
    }


    /**
     * 处理其他异常
     *
     * @param e otherException
     * @return HttpResponseInfo<String>
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponseInfo<String> exception(Exception e) {
        // 其他异常不做处理，输出异常信息即可
        log.error("未知异常 exception = {}", e.getMessage(), e);
        return HttpResponseInfo.error(HttpReturnCode.RC500.getCode(), HttpReturnCode.RC500.getMsg());
    }


    /**
     * 处理参数校验异常错误信息
     *
     * @param exception MethodArgumentNotValidException
     * @return String errorResult
     */
    private String combineErrors(MethodArgumentNotValidException exception) {
        Map<String, Object> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors.toString();
    }
}
