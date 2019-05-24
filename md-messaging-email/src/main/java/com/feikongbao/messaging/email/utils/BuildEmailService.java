package com.feikongbao.messaging.email.utils;

import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/29 14:39
 **/
@Service
public class BuildEmailService {

    @Value("${spring.mail.default-encoding}")
    private String encoding;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String enable;

    @Value("${mail.smtp.timeout}")
    private String timeout;

    /**
     * 动态创建用户邮件服务器
     * @param emailServiceEntity
     * @return
     */
    public JavaMailSenderImpl build(EmailServiceEntity emailServiceEntity) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        // 用户名
        javaMailSender.setUsername(emailServiceEntity.getUsername());
        // 密码
        javaMailSender.setPassword(emailServiceEntity.getPassword());
        // 地址
        javaMailSender.setHost(emailServiceEntity.getHost());
        // 编码
        javaMailSender.setDefaultEncoding(emailServiceEntity.getEncoding() == null ? encoding : emailServiceEntity.getEncoding());
        // 端口号
        if (emailServiceEntity.getPort() != null && emailServiceEntity.getPort() > 0){
            javaMailSender.setPort(emailServiceEntity.getPort());
        }
        // 协议
        javaMailSender.setProtocol(emailServiceEntity.getProtocol() == null ? "smtp" : emailServiceEntity.getProtocol());

        Properties javaMailProperties = new Properties();
        // 设置是否需要认证，如果为true,那么用户名和密码就必须的
        javaMailProperties.put("mail.smtp.auth", emailServiceEntity.getAuth() == null ? auth : emailServiceEntity.getAuth());
        // 是对纯文本通信协议的扩展。它提供一种方式将纯文本连接升级为加密连接（TLS或SSL），而不是另外使用一个端口作加密通信
        javaMailProperties.put("mail.smtp.starttls.enable", enable);
        // 超时
        javaMailProperties.put("mail.smtp.timeout", timeout);

        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }


}
