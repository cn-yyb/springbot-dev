package com.sdpzhong.dev.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.sdpzhong.dev.config.ComponentTest;
import com.sdpzhong.dev.config.bean.UserBean;
import com.sdpzhong.dev.entity.dto.UserLoginFormDto;
import com.sdpzhong.dev.entity.dto.UserLoginFormResponse;
import com.sdpzhong.dev.entity.po.User;
import com.sdpzhong.dev.http.BusinessException;
import com.sdpzhong.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    UserBean userBean;

    @Autowired
    ComponentTest componentTest;

    @SaCheckLogin
    @GetMapping("/getUserList")
    List<User> getUserList(@RequestParam(required = false, defaultValue = "") String username) {
        return userService.getUserList();
    }

    @SaCheckLogin
    @GetMapping("/getUserInfo")
    User getUserInfo(@RequestParam(required = false) String username) {
        userBean.sayHello();
        if (username != null) {
            componentTest.sayHello(username);
        }
        if (username == null || username.isEmpty()) {
            throw new BusinessException(400, "username 不能为空");
        }
        return userService.getUserByUsername(username);
    }

    @PostMapping("/login")
    UserLoginFormResponse login(@RequestBody UserLoginFormDto loginForm) {

        log.info("login form: {}", loginForm);
        return userService.userLogin(loginForm);
    }

    @PostMapping("/register")
    void register(@RequestBody User user) {

        log.info("register form: {}", user);
    }

    @NacosValue(value = "${isDev:false}", autoRefreshed = true)
    private boolean isDev;

    @NacosValue(value = "${port:7777}", autoRefreshed = true)
    private int port;

    @GetMapping("/getEnv")
    Map<String, Object> getEnv() {
        Map<String, Object> env = new TreeMap<>();

        env.put("isDev", isDev);

        env.put("port", port);

        return env;
    }


    @NacosInjected
    private NamingService namingService;

    @GetMapping("/getServiceByName")
    @ResponseBody
    public List<Instance> getServiceByName(@RequestParam String serviceName) throws NacosException {

        return namingService.getAllInstances(serviceName);
    }
}
