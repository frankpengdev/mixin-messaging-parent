package com.feikongbao.messaging.email.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feikongbao.messaging.core.aopaspect.MessageAckAop;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.receiver.ReceiverMessage;
import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.api.enums.MiMeTypeEnum;
import com.feikongbao.messaging.email.api.exception.EmailException;
import com.feikongbao.messaging.email.api.utils.MailUtils;
import com.feikongbao.messaging.email.utils.BuildUserDefinedMailboxService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description 通过RabbitMQ 发送邮件
 * @Author jinjun_luo
 * @Date 2019/4/11 15:42
 **/
@Service
public class SenderMailService implements ReceiverMessage {

    private static Logger logger = LoggerFactory.getLogger(SenderMailService.class);

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BuildUserDefinedMailboxService buildUserDefinedMailboxService;

    @Autowired
    private MailUtils mailUtils;

    /**
     * 发送邮件
     * @param message 消息：转成发送时的object
     * @param channel 通过 手于消息消费成功后确认
     * @throws Exception
     * //@Async
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = AbstractMessagingConstants.DIRECT_MQ_QUEUES_EMAIL, autoDelete = "false"),
                    exchange = @Exchange(value = AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_EMAIL, type = ExchangeTypes.DIRECT),
                    key = AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_EMAIL
            )
    )
    @MessageAckAop
    @RabbitHandler
    @Override
    public void onMessage(Message message, Channel channel) throws EmailException {
        try {
            MailEntity mailEntity = objectMapper.readValue(message.getBody(), MailEntity.class);
            //  非空参数校验
            mailUtils.parameterValidation(mailEntity);
            // 邮箱合法验证
            mailUtils.legalMailboxVerification(mailEntity);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // 封装邮件信息
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,!CollectionUtils.isEmpty(mailEntity.getAddAttachments()),"UTF-8");

            packageParameter(mimeMessageHelper, mailEntity);
            // 如果用户指定发送邮件的服务器，新创建邮件服务器发送，否则用本公司的邮件服务器发送
            EmailServiceEntity emailServiceEntity = mailEntity.getEmailServiceEntity();
            if (emailServiceEntity == null){
                 mailSender.send(mimeMessage);
            }else {
                // 参数校验
                mailUtils.validationEmailService(emailServiceEntity);
                // 邮件合法检验
                mailUtils.checkEmail(emailServiceEntity.getUsername());
                // 创建发送邮件服务器对象
                JavaMailSenderImpl javaMailSenderImpl = buildUserDefinedMailboxService.build(emailServiceEntity);
                // 发送
                javaMailSenderImpl.send(mimeMessage);
            }
            // 消费成功手动确认ACK 为false时表示此条消息消费成功，为true时表示所有消息消费成功,已使用AOP处理
        }catch (Exception e){
            logger.error("sending mail error == ");
            e.printStackTrace();
            throw new EmailException("messaging-email.system.excption.when.sending.mail");
        }
    }

    /**
     * 封闭参数
     * @param mimeMessageHelper
     * @param mailEntity
     */
    private void packageParameter(MimeMessageHelper mimeMessageHelper, MailEntity mailEntity) throws MessagingException {
        // 发送人
        mimeMessageHelper.setFrom(mailEntity.getFrom());
        // 收件人
        mimeMessageHelper.setTo(mailEntity.getTo().toArray(new String[mailEntity.getTo().size()]));
        // 抄送
        if (!CollectionUtils.isEmpty(mailEntity.getCc())){
            mimeMessageHelper.setCc(mailEntity.getCc().toArray(new String[mailEntity.getCc().size()]));
        }
        // mmh.setCc(mailEntity.getCc().stream().filter(Objects::nonNull).toArray(String[]::new));
        // 密送
        if (!CollectionUtils.isEmpty(mailEntity.getBcc())){
            mimeMessageHelper.setBcc(mailEntity.getBcc().toArray(new String[mailEntity.getBcc().size()]));
        }
        // 邮件标题
        mimeMessageHelper.setSubject(mailEntity.getSubject());
        // 邮件正文
        mimeMessageHelper.setText(mailEntity.getContent());
        // 发送时间
        mimeMessageHelper.setSentDate(new Date());
        // 添加附件
        if (!CollectionUtils.isEmpty(mailEntity.getAddAttachments())){
            Iterator<Map.Entry<String, byte[]>> iterator = mailEntity.getAddAttachments().entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, byte[]> next = iterator.next();
                mimeMessageHelper.addAttachment(next.getKey(), new ByteArrayDataSource(next.getValue(), MiMeTypeEnum.getBykey(mailUtils.getFileExt(next.getKey())).value));
            }
        }
    }
}