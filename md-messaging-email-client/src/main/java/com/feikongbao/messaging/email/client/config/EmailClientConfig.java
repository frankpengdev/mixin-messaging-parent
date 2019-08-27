package com.feikongbao.messaging.email.client.config;

import com.feikongbao.messaging.core.aopaspect.EnableMessagingCoreConfig;
import com.yodoo.megalodon.datasource.annotation.EnableEmailConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description 邮箱客户端配置
 * @Author jinjun_luo
 * @Date 2019/4/11 11:10
 **/
@Configuration
@ComponentScan(basePackages= {"com.feikongbao.messaging.email"})
@PropertySource(value = {"/com/feikongbao/messaging/email/client/messaging-email-client.properties"})
@EnableEmailConfig
@EnableMessagingCoreConfig
public class EmailClientConfig {

}
