package com.mixin.messaging.email.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mixin.messaging.core.aopaspect.MessageAckAop;
import com.mixin.messaging.core.constants.AbstractMessagingConstants;
import com.mixin.messaging.core.receiver.ReceiverMessage;
import com.mixin.messaging.email.entity.RabbitMqMailMessage;
import com.mixin.messaging.email.exception.EmailApiException;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

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
    public void onMessage(Message message, Channel channel) throws EmailApiException {
        /*try {
            RabbitMqMailMessage rabbitMqMailMessage = objectMapper.readValue(message.getBody(), RabbitMqMailMessage.class);
            //  非空参数校验
            mailUtils.parameterValidation(rabbitMqMailMessage);
            // 邮箱合法验证
            mailUtils.legalMailboxVerification(rabbitMqMailMessage);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // 封装邮件信息
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,!CollectionUtils.isEmpty(rabbitMqMailMessage.getAddAttachments()),"UTF-8");

            packageParameter(mimeMessageHelper, rabbitMqMailMessage);
            // 如果用户指定发送邮件的服务器，新创建邮件服务器发送，否则用本公司的邮件服务器发送
            EmailServiceEntity emailServiceEntity = rabbitMqMailMessage.getEmailServiceEntity();
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
            logger.error("sending mail error == ", JsonUtils.obj2json(e));
            throw new EmailApiException("messaging-email.system.excption.when.sending.mail");
        }*/
    }

    /**
     * 封闭参数
     * @param mimeMessageHelper
     * @param rabbitMqMailMessage
     */
    private void packageParameter(MimeMessageHelper mimeMessageHelper, RabbitMqMailMessage rabbitMqMailMessage) throws MessagingException {
        /*// 发送人
        mimeMessageHelper.setFrom(rabbitMqMailMessage.getFrom());
        // 收件人
        mimeMessageHelper.setTo(rabbitMqMailMessage.getTo().toArray(new String[rabbitMqMailMessage.getTo().size()]));
        // 抄送
        if (!CollectionUtils.isEmpty(rabbitMqMailMessage.getCc())){
            mimeMessageHelper.setCc(rabbitMqMailMessage.getCc().toArray(new String[rabbitMqMailMessage.getCc().size()]));
        }
        // mmh.setCc(rabbitMqMailMessage.getCc().stream().filter(Objects::nonNull).toArray(String[]::new));
        // 密送
        if (!CollectionUtils.isEmpty(rabbitMqMailMessage.getBcc())){
            mimeMessageHelper.setBcc(rabbitMqMailMessage.getBcc().toArray(new String[rabbitMqMailMessage.getBcc().size()]));
        }
        // 邮件标题
        mimeMessageHelper.setSubject(rabbitMqMailMessage.getSubject());
        // 邮件正文
        mimeMessageHelper.setText(rabbitMqMailMessage.getContent());
        // 发送时间
        mimeMessageHelper.setSentDate(new Date());
        // 添加附件
        if (!CollectionUtils.isEmpty(rabbitMqMailMessage.getAddAttachments())){
            Iterator<Map.Entry<String, byte[]>> iterator = rabbitMqMailMessage.getAddAttachments().entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, byte[]> next = iterator.next();
                mimeMessageHelper.addAttachment(next.getKey(), new ByteArrayDataSource(next.getValue(), MiMeTypeEnum.getBykey(mailUtils.getFileExt(next.getKey())).value));
            }
        }*/
    }
}