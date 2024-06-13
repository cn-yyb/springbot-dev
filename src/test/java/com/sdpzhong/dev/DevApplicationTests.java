package com.sdpzhong.dev;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;


@SpringBootTest
class DevApplicationTests {

    @Resource
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println("获取的数据库连接为:"+dataSource.getConnection());
    }

}
