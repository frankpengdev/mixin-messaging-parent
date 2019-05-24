package com.feikongbao.messaging.email.client.sender;

import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.api.exception.EmailException;
import com.feikongbao.messaging.email.api.utils.MailUtils;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.exception.MessagingCoreException;
import com.feikongbao.messaging.core.sender.SenderMessage;
import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/11 16:23
 **/
@Service
public class SenderMailService {

    private static Logger logger = LoggerFactory.getLogger(SenderMailService.class);

    @Autowired
    private SenderMessage<MailEntity> senderMessage;

    /**
     * @param from :发件人
     * @param to ：收件人
     * @param cc ：抄送人
     * @param bcc ：密送人
     * @param subject ：邮件标题
     * @param content ：邮件内容
     * @param attachment ：邮件附件
     * @param emailServiceEntity ：指定邮箱服务，如果有需要指定的邮件服务器传参，如果没有指定邮件服务器传 null
     * @throws EmailException
     */
    public void sendMail(String from, List<String> to, List<String> cc, List<String> bcc, String subject, String content, List<String> attachment, Long userId, EmailServiceEntity emailServiceEntity) throws EmailException,MessagingCoreException {
        // 非空参数参数校验
        MailUtils.parameterValidation(from,to);
        // 邮箱合法验证
        MailUtils.legalMailboxVerification(from, to, cc, bcc);
        // 封装参数
        MailEntity mailEntity = packageParameter(from, to, cc, bcc, subject, content, attachment);
        // 如果指定邮件服务器不为空，参数校验
        if (emailServiceEntity != null){
            MailUtils.validationEmailService(emailServiceEntity);
            mailEntity.setEmailServiceEntity(emailServiceEntity);
        }
        senderMessage.sendMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_EMAIL, AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_EMAIL, mailEntity, userId);
    }

    /**
     * 封装参数
     * @param from :发件人
     * @param to ：收件人
     * @param cc ：抄送人
     * @param bcc ：密送人
     * @param subject ：邮件标题
     * @param content ：邮件内容
     * @param attachment ：邮件附件
     * @return
     */
    public MailEntity packageParameter(String from, List<String> to, List<String> cc, List<String> bcc, String subject, String content, List<String> attachment) {
        MailEntity mailEntity = new MailEntity();
        if (StringUtils.isNotBlank(from)){
            mailEntity.setFrom(from);
        }
        if (!CollectionUtils.isEmpty(to)){
            mailEntity.setTo(to);
        }
        if (!CollectionUtils.isEmpty(cc)){
            mailEntity.setCc(cc);
        }
        if (!CollectionUtils.isEmpty(bcc)){
            mailEntity.setBcc(bcc);
        }
        if (StringUtils.isNotBlank(subject)){
            mailEntity.setSubject(subject);
        }
        if (StringUtils.isNotBlank(content)){
            mailEntity.setContent(content);
        }
        if (!CollectionUtils.isEmpty(attachment)){
            mailEntity.setFilePaths(attachment);
        }
        return mailEntity;
    }
}
