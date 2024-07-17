package com.sdpzhong.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdpzhong.dev.entity.dto.UserLoginFormDto;
import com.sdpzhong.dev.entity.dto.UserRegisterFormDto;
import com.sdpzhong.dev.entity.po.User;
import com.sdpzhong.dev.entity.vo.UserLoginResponseVo;

/**
 * @author zhongqing
 * @description 针对表【t_user(用户表)】的数据库操作Service
 * @createDate 2024-07-15 17:46:44
 */
public interface UserService extends IService<User> {
    /**
     * 账号登录
     *
     * @param loginForm
     * @return
     */
    UserLoginResponseVo userLogin(UserLoginFormDto loginForm);

    /**
     * 账号注册
     *
     * @param registerForm
     * @return
     */
    boolean userRegister(UserRegisterFormDto registerForm);

    /**
     * 获取账号信息
     *
     * @return
     */
    User getUserInfo();
}
