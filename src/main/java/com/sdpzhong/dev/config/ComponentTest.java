package com.sdpzhong.dev.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class ComponentTest {

    @PostConstruct
    public void init() {
        // 初始化操作
        log.info("ComponentTest init");
    }

    @PreDestroy
    public void destroy() {
        // 清理操作
        log.info("ComponentTest destroy");
    }

    public void sayHello(String name) {
        log.info("ComponentTest sayHello {}", name);
    }

}
