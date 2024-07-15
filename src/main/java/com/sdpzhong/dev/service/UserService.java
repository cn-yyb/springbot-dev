package com.sdpzhong.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdpzhong.dev.entity.dto.UserLoginFormDto;
import com.sdpzhong.dev.entity.po.User;
import com.sdpzhong.dev.entity.vo.UserInfoVo;
import com.sdpzhong.dev.entity.vo.UserLoginResponseVo;

/**
 * @author zhongqing
 * @description 针对表【t_user(用户表)】的数据库操作Service
 * @createDate 2024-07-15 17:46:44
 */
public interface UserService extends IService<User> {
    // 用户登录
    UserLoginResponseVo userLogin(UserLoginFormDto userLoginForm);

    // 用户注册

    // 获取用户信息
    UserInfoVo getUserInfo();
}
