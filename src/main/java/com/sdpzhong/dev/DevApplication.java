package com.sdpzhong.dev;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@Slf4j
@MapperScan("com.sdpzhong.dev.mapper")
@NacosPropertySource(dataId = "application-dev", autoRefreshed = true, type = ConfigType.YAML)
@EnableDiscoveryClient
@EnableAsync
@EnableTransactionManagement
public class DevApplication {

    public static void main(String[] args) {

        // SpringApplication.run(DevApplication.class, args);

        SpringApplication app = new SpringApplication(DevApplication.class);
        Environment environment = app.run(args).getEnvironment();

        log.info("Address: http://127.0.0.1:{}", environment.getProperty("server.port"));

        System.out.println("Application started successfully!!");
    }

}



