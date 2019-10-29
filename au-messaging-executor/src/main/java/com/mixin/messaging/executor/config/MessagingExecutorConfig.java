package com.mixin.messaging.executor.config;

import com.mixin.config.company.annotation.EnableCompanyEmailConfig;
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
 * @author Peng Fan
 * @date 10/28/2019
 */
@Configuration
@ComponentScan(basePackages= {"com.mixin.messaging.executor"})
@EnableCompanyRabbitMqConfig
@EnableCompanyEmailConfig
public class MessagingExecutorConfig implements InitializingBean {

    @Autowired
    @Qualifier("companyAmqpAdmin")
    private AmqpAdmin amqpAdmin;

    @Override
    public void afterPropertiesSet() {
        Queue queue = new Queue(EMAIL_MESSAGE_QUEUE_NAME, true, false, false);
        amqpAdmin.declareQueue(queue);
    }

}
