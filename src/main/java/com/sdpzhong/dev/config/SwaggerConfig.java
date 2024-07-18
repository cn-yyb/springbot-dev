package com.sdpzhong.dev.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    @NacosValue(value = "${server.port:9377}", autoRefreshed = true)
    private int port;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dev-service")
                .apiInfo(apiInfo())
                .protocols(newHashSet("https", "http"))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .select()
                .apis(
                        RequestHandlerSelectors.basePackage("com.sdpzhong.dev.controller")
                        // basePackage("com.sdpzhong.dev.controller")
                        //.basePackage(com.xx.xx.controller) 扫描指定包下的接口，最为常用
                        //.withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                        //.withMethodAnnotation(PostMapping.class) // 扫描带有指定注解的方法接口
                        //.any() // 扫描所有
                )
                .paths(PathSelectors.any()
                        //.any() // 满足条件的路径，该断言总为true
                        //.none() // 不满足条件的路径，该断言总为false（可用于生产环境屏蔽 swagger）
                        //.ant("/user/**") // 满足字符串表达式路径
                        //.regex("") // 符合正则的路径
                )
                .build();

    }


    /**
     * 支持的通讯协议集合
     *
     * @param type1
     * @param type2
     * @return Set<String>
     */
    private Set<String> newHashSet(String type1, String type2) {
        Set<String> set = new HashSet<>();
        set.add(type1);
        set.add(type2);
        return set;
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


    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey("Authorization", "Authorization", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .operationSelector(null)
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "Token 验证");
        return Collections.singletonList(new SecurityReference("Authorization", new AuthorizationScope[]{authorizationScope}));

    }
}
