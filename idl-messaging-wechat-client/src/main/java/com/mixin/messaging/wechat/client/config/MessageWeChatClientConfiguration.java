package com.mixin.messaging.wechat.client.config;

import com.mixin.messaging.core.config.MessagingCoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zili.wang
 * @date 2019/5/21 11:25
 */
@Configuration
@ComponentScan(basePackages = {"com.mixin.messaging.wechat.client"})
@Import({MessagingCoreConfig.class})
public class MessageWeChatClientConfiguration {

}
