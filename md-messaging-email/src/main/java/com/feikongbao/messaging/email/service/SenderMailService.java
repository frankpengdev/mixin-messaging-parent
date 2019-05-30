package com.feikongbao.messaging.email.service;

import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.api.exception.EmailException;
import com.feikongbao.messaging.email.api.utils.MailUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feikongbao.messaging.core.aopaspect.MessageAckAop;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.receiver.ReceiverMessage;
import com.feikongbao.messaging.email.utils.BuildUserDefinedMailboxService;
import com.rabbitmq.client.Channel;
import com.yodoo.megalodon.datasource.config.EmailConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @Description 通过RabbitMQ 发送邮件
 * @Author jinjun_luo
 * @Date 2019/4/11 15:42
 **/
@Service
public class SenderMailService implements ReceiverMessage {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BuildUserDefinedMailboxService buildUserDefinedMailboxService;

    @Autowired
    private EmailConfig emailConfig;

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
            // 如果用户自定义邮箱服务器就用用户的邮箱服务器，没定义用公司默认的服务器
            EmailServiceEntity emailServiceEntity = mailEntity.getEmailServiceEntity();
            if (emailServiceEntity == null){
                mailEntity.setFrom(emailConfig.emailServerUsername);
            }else {
                mailEntity.setFrom(emailServiceEntity.getUsername());
            }
            //  非空参数校验
            MailUtils.parameterValidation(mailEntity.getFrom(),mailEntity.getTo());
            // 邮箱合法验证
            MailUtils.legalMailboxVerification(mailEntity.getFrom(), mailEntity.getTo(), mailEntity.getCc(), mailEntity.getBcc());

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // 封装邮件信息
            MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage,!CollectionUtils.isEmpty(mailEntity.getFilePaths()),"UTF-8");

            packageParameter(mmh, mailEntity.getFrom(), mailEntity.getTo(), mailEntity.getCc(), mailEntity.getBcc(),mailEntity.getSubject(), mailEntity.getContent(), mailEntity.getFilePaths());
            // 如果用户指定发送邮件的服务器，新创建邮件服务器发送，否则用本公司的邮件服务器发送
            if (emailServiceEntity == null){
                 mailSender.send(mimeMessage);
            }else {
                // 参数校验
                MailUtils.validationEmailService(emailServiceEntity);
                // 创建发送邮件服务器对象
                JavaMailSenderImpl javaMailSenderImpl = buildUserDefinedMailboxService.build(emailServiceEntity);
                // 发送
                javaMailSenderImpl.send(mimeMessage);
            }
            // 消费成功手动确认ACK 为false时表示此条消息消费成功，为true时表示所有消息消费成功,已使用AOP处理
        }catch (Exception e){
            throw new EmailException("messaging-email.system.excption.when.sending.mail");
        }
    }

    /**
     * 封闭参数
     * @param mmh
     * @param from
     * @param to
     * @param cc
     * @param bcc
     * @param subject
     * @param content
     * @param filePaths
     */
    private void packageParameter(MimeMessageHelper mmh, String from, List<String> to, List<String> cc, List<String> bcc, String subject, String content, List<String> filePaths) throws MessagingException {
        // 发送人
        mmh.setFrom(from);
        // 收件人
        mmh.setCc(to.toArray(new String[to.size()]));
        // 抄送
        if (!CollectionUtils.isEmpty(cc)){
            mmh.setCc(cc.toArray(new String[cc.size()]));
        }
        // mmh.setCc(mailEntity.getCc().stream().filter(Objects::nonNull).toArray(String[]::new));
        // 密送
        if (!CollectionUtils.isEmpty(bcc)){
            mmh.setBcc(bcc.toArray(new String[bcc.size()]));
        }
        // 邮件标题
        if (StringUtils.isNotBlank(subject)){
            mmh.setSubject(subject);
        }
        // 邮件正文
        if (StringUtils.isNotBlank(content)){
            mmh.setText(content);
        }
        // 发送时间
        mmh.setSentDate(new Date());
        // 添加附件
        if (!CollectionUtils.isEmpty(filePaths)){
            for (String filePath : filePaths) {
                mmh.addAttachment(filePath.substring(filePath.lastIndexOf(File.separator)),new FileSystemResource(new File(filePath)));
            }
        }
    }
}