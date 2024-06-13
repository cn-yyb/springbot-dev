package com.sdpzhong.dev.controller;

import com.sdpzhong.dev.entity.dto.UserLoginFormDto;
import com.sdpzhong.dev.entity.dto.UserLoginFormResponse;
import com.sdpzhong.dev.entity.po.User;
import com.sdpzhong.dev.http.BusinessException;
import com.sdpzhong.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/getUserList")
    List<User> getUserList(@RequestParam(required = false, defaultValue = "") String username) {
        return userService.getUserList();
    }

    @GetMapping("/getUserInfo")
    User getUserInfo(@RequestParam(required = false) String username) {
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
