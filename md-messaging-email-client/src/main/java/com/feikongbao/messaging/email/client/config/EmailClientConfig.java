package com.feikongbao.messaging.email.client.config;

import com.feikongbao.messaging.core.config.MessagingCoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/11 11:10
 **/
@Configuration
@ComponentScan(basePackages= {"com.feikongbao.messaging.email.client"})
@PropertySource(value = {"/com/feikongbao/messaging/email/client/messaging-email-client.properties"})
@Import(MessagingCoreConfig.class)
public class EmailClientConfig {

    private final Logger logger = LoggerFactory.getLogger(EmailClientConfig.class);

}
