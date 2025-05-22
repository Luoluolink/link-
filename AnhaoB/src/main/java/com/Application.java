package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@MapperScan("com.Mapper") // 确保扫描你的 `UserMapper`
@ComponentScan({"com.Controller","com.Service","com.Config"})
@EnableJdbcHttpSession
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

