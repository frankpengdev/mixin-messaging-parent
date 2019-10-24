package com.mixin.messaging.email.client.config;

import com.mixin.config.company.annotation.EnableCompanyEmailConfig;
import com.mixin.messaging.core.aopaspect.EnableMessagingCoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description 邮箱客户端配置
 * @Author jinjun_luo
 * @Date 2019/4/11 11:10
 **/
@Configuration
@ComponentScan(basePackages= {"com.mixin.messaging.email"})
@PropertySource(value = {"/com/feikongbao/messaging/email/client/messaging-email-client.properties"})
@EnableCompanyEmailConfig
@EnableMessagingCoreConfig
public class EmailClientConfig {

}
