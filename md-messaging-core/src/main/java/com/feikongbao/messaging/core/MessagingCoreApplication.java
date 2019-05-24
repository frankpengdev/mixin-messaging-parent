package com.feikongbao.messaging.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/9 17:44
 **/
@SpringBootApplication
@MapperScan("com.feikongbao.messaging.core.mapper")
public class MessagingCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingCoreApplication.class, args);
	}
}
