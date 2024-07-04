package com.sdpzhong.dev.config.bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserBean {

    public void sayHello() {
        log.info("UserBean: hello");
    }

    public void init() {
        log.info("UserBean init");
    }

    public void destroy() {
        log.info("UserBean: destroy");
    }
}
