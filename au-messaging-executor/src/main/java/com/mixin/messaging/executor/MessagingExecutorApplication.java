package com.mixin.messaging.executor;

import com.mixin.messaging.executor.config.MessagingExecutorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author Peng Fan
 * @date 10/28/2019
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import(MessagingExecutorConfig.class)
public class MessagingExecutorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessagingExecutorApplication.class, args);
    }
}
