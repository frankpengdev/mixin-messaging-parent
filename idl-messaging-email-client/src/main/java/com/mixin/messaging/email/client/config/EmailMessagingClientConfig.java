package com.mixin.messaging.email.client.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.mixin.config.company.annotation.EnableCompanyRabbitMqConfig;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.mixin.messaging.constant.QueueNames.EMAIL_MESSAGE_QUEUE_NAME;

/**
 * @Description 邮箱客户端配置
 * @Author jinjun_luo
 * @Date 2019/4/11
 **/
@Configuration
@ComponentScan(basePackages= {"com.mixin.messaging.email.client"})
@EnableCompanyRabbitMqConfig
@EnableApolloConfig("application")
public class EmailMessagingClientConfig implements InitializingBean {

    @Autowired
    @Qualifier("companyAmqpAdmin")
    private AmqpAdmin amqpAdmin;

    @Override
    public void afterPropertiesSet() {
        Queue queue = new Queue(EMAIL_MESSAGE_QUEUE_NAME, true, false, false);
        amqpAdmin.declareQueue(queue);
    }
}
