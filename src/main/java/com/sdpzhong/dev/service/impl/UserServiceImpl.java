package com.sdpzhong.dev.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdpzhong.dev.entity.dto.UserLoginFormDto;
import com.sdpzhong.dev.entity.dto.UserLoginFormResponse;
import com.sdpzhong.dev.entity.po.User;
import com.sdpzhong.dev.http.BusinessException;
import com.sdpzhong.dev.mapper.UserMapper;
import com.sdpzhong.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        List<User> res = userMapper.selectList(null);
        log.info("getUserList: {}", res);
        return res;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        log.info("getUserByUsername: {}", user);
        return user;
    }

    @Override
    public UserLoginFormResponse userLogin(UserLoginFormDto userLoginForm) {

        User user = userMapper.findOneByUsername(userLoginForm.getUsername());

        log.info("userLogin: {}", user);

        if (user == null) {
            throw new BusinessException(1, "用户未注册");
        }

        if (!Objects.equals(user.getPassword(), userLoginForm.getPassword())) {
            throw new BusinessException(2, "账号或密码错误");
        }

        UserLoginFormResponse res = new UserLoginFormResponse();
        res.setToken("114514").setExpireTime("2024-03-11 11:11:11");
        return res;
    }
}
