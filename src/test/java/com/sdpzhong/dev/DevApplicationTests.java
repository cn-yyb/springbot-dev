package com.sdpzhong.dev;

import com.sdpzhong.dev.entity.po.User;
import com.sdpzhong.dev.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;


@SpringBootTest
class DevApplicationTests {

    @Resource
    private DataSource dataSource;

    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println("获取的数据库连接为:" + dataSource.getConnection());
    }

    @Test
    public void deleteUser() {
        int result = userMapper.deleteById("2c4c769d703effd5b94cf6bffe35178d");

        System.out.println(result);
    }

    @Test
    public void queryUser() {
        User result = userMapper.selectById("2c4c769d703effd5b94cf6bffe35178d");

        System.out.println(result);
    }

}
