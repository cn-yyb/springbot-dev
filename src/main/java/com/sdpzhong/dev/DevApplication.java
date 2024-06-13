package com.sdpzhong.dev;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan("com.sdpzhong.dev.mapper")
@Slf4j
public class DevApplication {

    public static void main(String[] args) {

        //  SpringApplication.run(DevApplication.class, args);

        SpringApplication app = new SpringApplication(DevApplication.class);
        Environment environment = app.run(args).getEnvironment();

        log.info("Application started successfully!! ");
        log.info("Address: http://127.0.0.1:{}", environment.getProperty("server.port"));

        System.out.println("Application started successfully!!");
    }

}



