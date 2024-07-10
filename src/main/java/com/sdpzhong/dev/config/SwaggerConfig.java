package com.sdpzhong.dev.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @NacosValue(value = "${server.port:9377}", autoRefreshed = true)
    private int port;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dev-service")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sdpzhong.dev.controller")
                        // basePackage("com.sdpzhong.dev.controller")
                        //.basePackage(com.xx.xx.controller) 扫描指定包下的接口，最为常用
                        //.withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                        //.withMethodAnnotation(PostMapping.class) // 扫描带有指定注解的方法接口
                        //.any() // 扫描所有
                )
                .paths(PathSelectors.any()
                        //.any() // 满足条件的路径，该断言总为true
                        //.none() // 不满足条件的路径，该断言总为false（可用于生成环境屏蔽 swagger）
                        //.ant("/user/**") // 满足字符串表达式路径
                        //.regex("") // 符合正则的路径
                )
                .build();
    }

    /**
     * create ApiInfo
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {

        Contact contact = new Contact(
                "sdpzhong",
                "https://www.baidu.com",
                "sdpzhong@163.com");

        return new ApiInfoBuilder()
                .title("DevService")
                .description("DevService Swagger Document")
                .termsOfServiceUrl("http://127.0.0.1:" + port)
                .version("1.0")
                .license("Hello World!")
                .licenseUrl("https://www.baidu.com")
                .contact(contact)
                .build(); //
    }
}
