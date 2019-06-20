package com.feikongbao.messaging.email.client.sender;

import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.exception.MessagingCoreException;
import com.feikongbao.messaging.core.sender.SenderMessage;
import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.api.exception.EmailException;
import com.feikongbao.messaging.email.api.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 邮箱客户端发送
 * @Author jinjun_luo
 * @Date 2019/4/11 16:23
 **/
@Service
public class SenderMailService {

    @Autowired
    private SenderMessage<MailEntity> senderMessage;

    @Autowired
    private MailUtils mailUtils;

    /**
     * 发送方法
     * @param mailEntity
     * @throws EmailException
     */
    public void sendMail(MailEntity mailEntity) throws EmailException,MessagingCoreException {
        // 非空参数参数校验：发件人和收件人不能为空
        mailUtils.parameterValidation(mailEntity);
        // 邮箱合法验证
        mailUtils.legalMailboxVerification(mailEntity);
        // 如果指定邮件服务器不为空，参数校验
        if (mailEntity.getEmailServiceEntity() != null){
            // 检验邮箱参数是否为空
            mailUtils.validationEmailService(mailEntity.getEmailServiceEntity());
            // 邮件合法检验
            mailUtils.checkEmail(mailEntity.getEmailServiceEntity().getUsername());
            mailEntity.setEmailServiceEntity(mailEntity.getEmailServiceEntity());
        }
        senderMessage.sendMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_EMAIL, AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_EMAIL, mailEntity, mailEntity.getUserId());
    }
}
