package com.feikongbao.messaging.email.api.utils;

import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import com.feikongbao.messaging.email.api.exception.EmailException;
import com.yodoo.megalodon.datasource.config.EmailConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description 邮箱合法校验和非空参数校验
 * @Author jinjun_luo
 * @Date 2019/4/23 10:58
 **/
@Component
public class MailUtils {

    @Autowired
    private EmailConfig emailConfig;

    /**
     * 非空参数校验 ： 发送和接收的邮箱
     * @param from
     * @param to
     * @throws EmailException
     */
    public void parameterValidation(String from, List<String> to) throws EmailException {
        if (StringUtils.isBlank(from)){
            throw new EmailException("messaging-email.the.person.sending.the.email.is.empty");
        }
        parameterValidation(to);
    }

    /**
     * 非空参数校验 ： 接收的邮箱
     * @param to
     * @throws EmailException
     */
    public void parameterValidation(List<String> to) throws EmailException {
        if (to == null || to.size() <= 0){
            throw new EmailException("messaging-email.the.person.receiving.the.email.is.empty");
        }
    }

    /**
     * 邮件合法性验正
     * @param from
     * @param to
     * @param cc
     * @param bcc
     */
    public void legalMailboxVerification(String from, List<String> to, List<String> cc, List<String> bcc)throws EmailException {
        // 发件人
        if (StringUtils.isNotBlank(from)){
            checkEmail(from);
        }
        // 收件人
        if (to != null && to.size() > 0){
            for (String t : to) {
                checkEmail(t);
            }
        }
        // 抄送人
        if (cc != null && cc.size() > 0){
            for (String c : cc) {
                checkEmail(c);
            }
        }
        // 密送人
        if (bcc != null && bcc.size() > 0){
            for (String b : bcc) {
                checkEmail(b);
            }
        }
    }

    /**
     * 正则验正邮箱合法性
     * @param email
     * @throws EmailException
     */
    public void checkEmail(String email) throws EmailException {
        if (!Pattern.compile(emailConfig.emailServerMailboxRegularExpression).matcher(email).matches()){
            throw new EmailException("messaging-email.is.not.a.legitimate.mailbox",email);
        }
    }

    /**
     * 校验用户指定的邮件服务器参数
     * @param emailServiceEntity
     * @throws EmailException
     */
    public void validationEmailService(EmailServiceEntity emailServiceEntity) throws EmailException {
        // 指定邮件服务器地址
        if (StringUtils.isBlank(emailServiceEntity.getHost())){
            throw new EmailException("messaging-email.user.specified.mail.server.address.is.empty");
        }
        // 指定邮件服务器用户名
        if (StringUtils.isBlank(emailServiceEntity.getUsername())){
            throw new EmailException("messaging-email.user.specified.mail.server.username.is.empty");
        }
        // 指定邮件服务器密码
        if (StringUtils.isBlank(emailServiceEntity.getPassword())){
            throw new EmailException("messaging-email.user.specified.mail.server.password.is.empty");
        }
    }
}