package com.sdpzhong.dev.controller;

import com.sdpzhong.dev.entity.dto.UserLoginFormDto;
import com.sdpzhong.dev.entity.po.User;
import com.sdpzhong.dev.entity.vo.UserInfoVo;
import com.sdpzhong.dev.entity.vo.UserLoginResponseVo;
import com.sdpzhong.dev.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "用户模块", description = "用户相关接口模块")
public class UserController {

    private final UserService userService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public UserLoginResponseVo login(@RequestBody @Validated UserLoginFormDto loginForm) {
        return userService.userLogin(loginForm);
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public void register(@RequestBody User user) {
        log.info("register form: {}", user);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public UserInfoVo getUserInfo() {
        return userService.getUserInfo();
    }
}
