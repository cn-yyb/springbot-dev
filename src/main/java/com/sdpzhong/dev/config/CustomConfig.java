package com.sdpzhong.dev.config;


import com.sdpzhong.dev.config.bean.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CustomConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public UserBean testBean() {
        return new UserBean();
    }
}
