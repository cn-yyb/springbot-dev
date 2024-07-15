package com.sdpzhong.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdpzhong.dev.entity.dto.UserLoginFormDto;
import com.sdpzhong.dev.entity.po.User;
import com.sdpzhong.dev.entity.vo.UserInfoVo;
import com.sdpzhong.dev.entity.vo.UserLoginResponseVo;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getUserList();

    // 用户登录
    UserLoginResponseVo userLogin(UserLoginFormDto userLoginForm);

    // 用户注册

    // 获取用户信息
    UserInfoVo getUserInfo();

}
