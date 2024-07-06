package com.pj;

import cn.dev33.satoken.SaManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zhongqing
 * @Description: satoken 初始化
 * @Date: 2024-07-05 10:42
 **/

@SpringBootApplication
public class SaTokenApplication {
    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(SaTokenApplication.class, args);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
    }
}
