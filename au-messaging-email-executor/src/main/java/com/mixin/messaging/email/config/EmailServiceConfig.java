package com.mixin.messaging.email.config;

import com.mixin.config.company.annotation.EnableCompanyEmailConfig;
import com.mixin.messaging.core.aopaspect.EnableMessagingCoreConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @Description 邮箱服务器配置
 * @Author jinjun_luo
 * @Date 2019/4/17 11:07
// **/
@Configuration
@ComponentScan(basePackages= {"com.mixin.messaging.email"})
@EnableCompanyEmailConfig
@EnableMessagingCoreConfig
public class EmailServiceConfig {

    /**
     * 创建 JavaMailSenderImpl bean
     * @return
     */
    @Bean
    public JavaMailSenderImpl getJavaMailSenderImpl(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        // 用户名
//        javaMailSender.setUsername(emailConfig.emailServerUsername);
//        // 密码
//        javaMailSender.setPassword(emailConfig.emailServerPassword);
//        // 地址
//        javaMailSender.setHost(emailConfig.emailServerHost);
//        // 编码
//        javaMailSender.setDefaultEncoding(emailConfig.emailServerTextDefaultEncoding);
        // javaMailSender.setPort(port);
        // javaMailSender.setProtocol(protocol)
        Properties javaMailProperties = new Properties();
        // 设置是否需要认证，如果为true,那么用户名和密码就必须的
//        javaMailProperties.put("mail.smtp.auth", emailConfig.emailServerSmtpAuth);
//        // 是对纯文本通信协议的扩展。它提供一种方式将纯文本连接升级为加密连接（TLS或SSL），而不是另外使用一个端口作加密通信
//        javaMailProperties.put("mail.smtp.starttls.enable", emailConfig.emailServerSmtpStarttlsEnable);
//        // 超时
//        javaMailProperties.put("mail.smtp.timeout", emailConfig.emailServerSmtpTimeout);
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }
}