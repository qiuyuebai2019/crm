package com.qust;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类入口.
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月25日 09时15分
 */

@SpringBootApplication
@MapperScan("com.qust.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
