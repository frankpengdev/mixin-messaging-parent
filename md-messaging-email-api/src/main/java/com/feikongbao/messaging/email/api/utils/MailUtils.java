package com.feikongbao.messaging.email.api.utils;

import com.feikongbao.messaging.email.api.constants.AbstractMailConstants;
import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import com.feikongbao.messaging.email.api.exception.EmailException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/23 10:58
 **/
public class MailUtils {

    /**
     * 非空参数校验
     * @param from
     * @param to
     * @throws EmailException
     */
    public static void parameterValidation(String from, List<String> to) throws EmailException {
        if (StringUtils.isBlank(from)){
            throw new EmailException("messaging-email.the.person.sending.the.email.is.empty");
        }
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
    public static void legalMailboxVerification(String from, List<String> to, List<String> cc, List<String> bcc)throws EmailException {
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
    public static void checkEmail(String email) throws EmailException {
        if (!Pattern.compile(AbstractMailConstants.RULE_EMAIL).matcher(email).matches()){
            throw new EmailException("messaging-email.is.not.a.legitimate.mailbox",email);
        }
    }

    /**
     * 校验用户指定的邮件服务器参数
     * @param emailServiceEntity
     * @throws EmailException
     */
    public static void validationEmailService(EmailServiceEntity emailServiceEntity) throws EmailException {
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
