package com.mixin.messaging.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author jinjun_luo
 * @Date 2019/4/9 17:44
 **/
@SpringBootApplication
@MapperScan("com.mixin.messaging.core.mapper")
public class MessagingCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingCoreApplication.class, args);
	}
}
