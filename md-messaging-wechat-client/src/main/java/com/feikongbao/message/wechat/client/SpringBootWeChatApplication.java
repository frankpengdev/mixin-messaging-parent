package com.feikongbao.message.wechat.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zili.wang
 * @date 2019/5/21 11:19
 */
@SpringBootApplication(scanBasePackages = {"com.feikongbao.message.wechat.client"})
public class SpringBootWeChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWeChatApplication.class,args);
    }

}
