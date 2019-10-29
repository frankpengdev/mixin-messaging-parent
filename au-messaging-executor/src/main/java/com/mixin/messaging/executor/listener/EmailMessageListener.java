package com.mixin.messaging.executor.listener;

import com.mixin.messaging.email.entity.RabbitMqMailMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

import static com.mixin.messaging.constant.QueueNames.EMAIL_MESSAGE_QUEUE_NAME;

/**
 * @author Peng Fan
 * @date 10/28/2019
 */
@Component
public class EmailMessageListener {

    private static Logger logger = LoggerFactory.getLogger(EmailMessageListener.class);

    @Autowired
    @Qualifier("companyMailSender")
    public JavaMailSender mailSender;

    @RabbitListener(queues = EMAIL_MESSAGE_QUEUE_NAME)
    public void onMailMessage(RabbitMqMailMessage mailMessage) {
        try {
            sendEmail(mailMessage);
        } catch (MessagingException e) {
            logger.error("Fail to send the email:", e);
        }
    }

    private void sendEmail(RabbitMqMailMessage mailMessage) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        if (CollectionUtils.isEmpty(mailMessage.getAttachments())) {
            helper = new MimeMessageHelper(mimeMessage, false);
        } else {
            helper = new MimeMessageHelper(mimeMessage, true);
            for (Map.Entry<String, byte[]> attachment : mailMessage.getAttachments().entrySet()) {
                helper.addAttachment(attachment.getKey(), new ByteArrayResource(attachment.getValue()));
            }
        }
        helper.setTo(mailMessage.getRecipients().toArray(new String[]{}));
        if (!CollectionUtils.isEmpty(mailMessage.getBccRecipients())) {
            helper.setBcc(mailMessage.getBccRecipients().toArray(new String[]{}));
        }
        helper.setSubject(mailMessage.getSubject());
        helper.setText(mailMessage.getContent());
        if (!StringUtils.isEmpty(mailMessage.getSender())) {
            helper.setFrom(mailMessage.getSender());
        }
        mailSender.send(mimeMessage);
    }


}
