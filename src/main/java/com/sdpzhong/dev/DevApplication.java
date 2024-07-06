package com.sdpzhong.dev;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@MapperScan("com.sdpzhong.dev.mapper")
@NacosPropertySource(dataId = "application-dev", autoRefreshed = true, type = ConfigType.YAML)
public class DevApplication {

    public static void main(String[] args) {

//        SpringApplication.run(DevApplication.class, args);

        SpringApplication app = new SpringApplication(DevApplication.class);
        Environment environment = app.run(args).getEnvironment();

        log.info("Address: http://127.0.0.1:{}", environment.getProperty("server.port"));

        System.out.println("Application started successfully!!");
    }

}



