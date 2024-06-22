package com.sdpzhong.dev.controller;

import com.sdpzhong.dev.entity.dto.RedisQueryResponseDto;
import com.sdpzhong.dev.http.HttpResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController<e> {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @GetMapping("/set")
    public String setKey(@RequestParam(required = true) String key, @RequestParam Object value) {
        log.info("setKey:{}--{}", key, value);
        redisTemplate.opsForValue().set(key, value);
        return "success";

    }

    @GetMapping("/get")
    public Object getKey(@RequestParam(required = false) String key) {
        log.info("get key: {}", key);
        Object value = redisTemplate.opsForValue().get(key);
        log.info("query redis value: {}", value);


        RedisQueryResponseDto res = new RedisQueryResponseDto();

        if (Objects.isNull(value)) {

            return HttpResponseInfo.error(404, "not found");
        }

        return res.setKey(key).setValue((String) value);
    }
}