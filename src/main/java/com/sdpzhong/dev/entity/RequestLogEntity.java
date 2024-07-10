package com.sdpzhong.dev.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdpzhong.dev.utils.LogTemplate;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Data
@Accessors(chain = true)
@Slf4j
public class RequestLogEntity {


    /**
     * method: 请求接口路径
     */
    public String url;

    /**
     * method: 请求方法类型
     */
    public String method;

    /**
     * ip: 请求IP
     */
    public String ip;

    /**
     * token
     */
    public String token;

    /**
     * queryString: 链接参数
     */
    public String queryString;

    /**
     * statusCode: 响应状态码
     */
    public Object statusCode;

    /**
     * statusCode: 请求参数
     */
    public Object[] arguments;

    /**
     * result: 返回值
     */
    public Object result;

    /**
     * error: 错误信息
     */
    public Object error;

    /**
     * date: 日期
     */
    public String date;


    public RequestLogEntity() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = currentDate.format(formatter);
    }


    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public void consoleLog() {
//        log.info("========================================================================>\n" +
//                        "* Url: {}\n" +
//                        "* Method: {}\n" +
//                        "* Token: {}\n" +
//                        "* IP: {}\n" +
//                        "* Status Code: {}\n" +
//                        "* Query: {}\n" +
//                        "* Arguments: {}\n" +
//                        "* Result: {}\n" +
//                        "* Date: {}\n" +
//                        "========================================================================>",
//                url, method, token, ip, statusCode, queryString, arguments, result, date);
        ObjectMapper objectMapper = new ObjectMapper();
        Map origin = objectMapper.convertValue(this, Map.class);
        log.info(LogTemplate.createLogTemplate(origin));
    }
}
