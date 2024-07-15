package com.sdpzhong.dev.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdpzhong.dev.entity.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 获取用户信息
    User queryUserByUsername(String username);

    @Select("SELECT * FROM users WHERE account_name = #{username}")
    User findOneByUsername(@Param("username") String username);
}
