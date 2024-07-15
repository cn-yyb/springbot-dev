package com.sdpzhong.dev.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdpzhong.dev.common.HttpReturnCode;
import com.sdpzhong.dev.entity.dto.UserLoginFormDto;
import com.sdpzhong.dev.entity.po.User;
import com.sdpzhong.dev.entity.vo.UserInfoVo;
import com.sdpzhong.dev.entity.vo.UserLoginResponseVo;
import com.sdpzhong.dev.http.BusinessException;
import com.sdpzhong.dev.mapper.UserMapper;
import com.sdpzhong.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<User> getUserList() {
        String data = (String) redisTemplate.opsForValue().get("username");
        log.info("redis data: {}", data);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(User::getUserId, User::getAccountName).lt(User::getUserId, 10);
        List<User> res = userMapper.selectList(queryWrapper);
        log.info("getUserList: {}", res);

        return res;
    }


    @Override
    public UserLoginResponseVo userLogin(UserLoginFormDto userLoginForm) {

        // User user = userMapper.findOneByUsername(userLoginForm.getUsername());

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper
                .select(User::getPassword, User::getUserId, User::getAccountName)
                .eq(User::getAccountName, userLoginForm.getUsername());

        User user = getOne(queryWrapper);

        log.info("userLogin: {}", user);

        if (user == null) {
            throw new BusinessException(HttpReturnCode.RC_NO_REGISTERED);
        }

        if (!Objects.equals(user.getPassword(), userLoginForm.getPassword())) {
            throw new BusinessException(HttpReturnCode.RC_LOGIN_REJECTED);
        }

        // 存储用户id，并生成 token
        StpUtil.login(user.getUserId());

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return new UserLoginResponseVo()
                .setToken(tokenInfo.tokenValue)
                .setTimeout(tokenInfo.tokenTimeout);
    }

    @Override
    public UserInfoVo getUserInfo() {
        // 通过token查询出用户id
        Object userId = StpUtil.getLoginId();

        log.info("getUserInfo: {}", userId);

        User user = this.getById((Serializable) userId);

        if (user == null) {
            throw new BusinessException(HttpReturnCode.RC_NO_REGISTERED);
        }

        return new UserInfoVo()
                .setUsername(user.getAccountName())
                .setUid(user.getUid())
                .setMobile(user.getMobile())
                .setGender(user.getGender());
    }

}
