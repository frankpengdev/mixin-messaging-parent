package com.feikongbao.messaging.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 用户启动服务
 * @Author jinjun_luo
 * @Date 2019/4/11 15:42
 * //@EnableAsync
 **/
@Configuration
@EnableAutoConfiguration
@ComponentScan(value = "com.feikongbao.messaging.email")
@SpringBootApplication
public class EmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

}
