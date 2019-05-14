package com.feikongbao.messaging.email.config;

import com.feikongbao.messaging.core.config.MessagingCoreConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/17 11:07
// **/
@Configuration
@ComponentScan(basePackages= {"com.feikongbao.messaging.email"})
@PropertySource({"/com/feikongbao/messaging/email/messaging-email.properties"})
@Import(MessagingCoreConfig.class)
public class EmailServiceConfig {

    @Value("${spring.mail.default-encoding}")
    private String encoding;

    @Value("${spring.mail.host}")
    private String host;

    // @Value("${spring.mail.port}")
    // private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String enable;

    @Value("${mail.smtp.timeout}")
    private String timeout;

    /**
     * 创建 JavaMailSenderImpl bean
     * @return
     */
    @Bean
    public JavaMailSenderImpl getJavaMailSenderImpl(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        // 用户名
        javaMailSender.setUsername(username);
        // 密码
        javaMailSender.setPassword(password);
        // 地址
        javaMailSender.setHost(host);
        // 编码
        javaMailSender.setDefaultEncoding(encoding);
        // javaMailSender.setPort(port);
        // javaMailSender.setProtocol(protocol)
        Properties javaMailProperties = new Properties();
        // 设置是否需要认证，如果为true,那么用户名和密码就必须的
        javaMailProperties.put("mail.smtp.auth", auth);
        // 是对纯文本通信协议的扩展。它提供一种方式将纯文本连接升级为加密连接（TLS或SSL），而不是另外使用一个端口作加密通信
        javaMailProperties.put("mail.smtp.starttls.enable", enable);
        // 超时
        javaMailProperties.put("mail.smtp.timeout", timeout);
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }

}
