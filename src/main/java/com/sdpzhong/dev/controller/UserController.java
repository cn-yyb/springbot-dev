package com.sdpzhong.dev.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
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
}
