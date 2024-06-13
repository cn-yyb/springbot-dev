package com.sdpzhong.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdpzhong.dev.entity.dto.UserLoginFormDto;
import com.sdpzhong.dev.entity.dto.UserLoginFormResponse;
import com.sdpzhong.dev.entity.po.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getUserList();

    // 获取用户信息
    User getUserByUsername(String username);

    // 用户登录
    UserLoginFormResponse userLogin(UserLoginFormDto userLoginForm);

    // 用户注册

}
