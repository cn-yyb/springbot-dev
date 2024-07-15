package com.sdpzhong.dev.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdpzhong.dev.entity.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhongqing
 * @description 针对表【t_user(用户表)】的数据库操作Mapper
 * @createDate 2024-07-15 17:31:25
 * @Entity com.sdpzhong.dev.entity.po.User
 */
public interface UserMapper extends BaseMapper<User> {
    // 获取用户信息
    User queryUserByUsername(String username);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findOneByUsername(@Param("username") String username);
}



