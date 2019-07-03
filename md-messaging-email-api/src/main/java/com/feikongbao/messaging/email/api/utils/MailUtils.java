package com.feikongbao.messaging.email.api.utils;

import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.api.enums.MiMeTypeEnum;
import com.feikongbao.messaging.email.api.exception.EmailException;
import com.yodoo.megalodon.datasource.config.EmailConfig;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
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

    public final static String fileExt = ".";

    /**
     * 非空参数校验 ： 发送和接收的邮箱
     * @param mailEntity
     * @throws EmailException
     */
    public void parameterValidation(MailEntity mailEntity) throws EmailException {
        // 发件人
        if (mailEntity == null || StringUtils.isBlank(mailEntity.getFrom())) {
            throw new EmailException("messaging-email.the.person.sending.the.email.is.empty");
        }
        // 收件人
        if (mailEntity == null || mailEntity.getTo() == null || mailEntity.getTo().size() <= 0) {
            throw new EmailException("messaging-email.the.person.receiving.the.email.is.empty");
        }
        // 标题
        if (mailEntity == null || StringUtils.isBlank(mailEntity.getSubject())){
            throw new EmailException("messaging-email.user.send.mail.the.subject.is.empty");
        }
        // 正文
        if (mailEntity == null || StringUtils.isBlank(mailEntity.getContent())){
            throw new EmailException("messaging-email.user.send.mail.the.content.is.empty");
        }
        // 如果有附件，附件名要符合发送条件，附件类型在MiMeTypeEnum类中
        if (mailEntity.getAddAttachments() != null && mailEntity.getAddAttachments().size() > 0) {
            Iterator<Map.Entry<String, byte[]>> iterator = mailEntity.getAddAttachments().entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, byte[]> next = iterator.next();
                if (StringUtils.isBlank(MiMeTypeEnum.getBykey(getFileExt(next.getKey())).value)) {
                    throw new EmailException("messaging-email.attachment.type.error attachmentName: (0)", next.getKey());
                }
            }
        }
    }

    /**
     * 邮件合法性验正
     * @param mailEntity
     */
    public void legalMailboxVerification(MailEntity mailEntity)throws EmailException {
        // 发件人
        if (mailEntity != null && StringUtils.isNotBlank(mailEntity.getFrom())){
            checkEmail(mailEntity.getFrom());
        }
        // 收件人
        if (mailEntity != null && mailEntity.getTo() != null && mailEntity.getTo().size() > 0){
            for (String to : mailEntity.getTo()) {
                checkEmail(to);
            }
        }
        // 抄送人
        if (mailEntity != null && mailEntity.getCc() != null && mailEntity.getCc().size() > 0){
            for (String cc : mailEntity.getCc()) {
                checkEmail(cc);
            }
        }
        // 密送人
        if (mailEntity != null && mailEntity.getBcc() != null && mailEntity.getBcc().size() > 0){
            for (String bcc : mailEntity.getBcc()) {
                checkEmail(bcc);
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
        if (emailServiceEntity == null || StringUtils.isBlank(emailServiceEntity.getHost())){
            throw new EmailException("messaging-email.user.specified.mail.server.address.is.empty");
        }
        // 指定邮件服务器用户名
        if (emailServiceEntity == null || StringUtils.isBlank(emailServiceEntity.getUsername())){
            throw new EmailException("messaging-email.user.specified.mail.server.username.is.empty");
        }
        // 指定邮件服务器密码
        if (emailServiceEntity == null || StringUtils.isBlank(emailServiceEntity.getPassword())){
            throw new EmailException("messaging-email.user.specified.mail.server.password.is.empty");
        }
    }

    /**
     * 获取文件后缀名（不带点）.
     * @return 如："jpg" or "".
     */
    public String getFileExt(String fileName) {
        if (StringUtils.isBlank(fileName) || !fileName.contains(fileExt)) {
            return "";
        } else {
            // 不带最后的点
            return FilenameUtils.getExtension(fileName);
            //return fileName.substring(fileName.lastIndexOf(fileExt));
        }
    }
}