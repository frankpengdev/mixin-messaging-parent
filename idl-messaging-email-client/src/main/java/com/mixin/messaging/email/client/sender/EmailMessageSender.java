package com.mixin.messaging.email.client.sender;


import com.mixin.messaging.email.client.exception.EmailSenderClientException;
import com.mixin.messaging.email.entity.RabbitMqMailMessage;
import com.mixin.messaging.email.exception.EmailApiException;
import com.mixin.messaging.email.utils.RabbitMqMailMessageValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

import static com.mixin.messaging.constant.QueueNames.EMAIL_MESSAGE_QUEUE_NAME;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.springframework.amqp.core.MessageDeliveryMode.PERSISTENT;

/**
 * This is the service component the client program should use to send email messages asynchronously,
 * this component will deliver the email message to RabbitMQ message broker and then returns,
 * there is an application sitting on the other side of the message broker to actually send the email via SMTP service.
 *
 * @Author jinjun_luo
 * @Date 2019/4/11
 **/
@Component
public class EmailMessageSender {

    @Autowired
    @Qualifier("companyRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Value("${system_admin_email_address}")
    private String sysAdminEmailAddress;

    private MessagePostProcessor messagePostProcessor = message -> {
        MessageProperties messageProperties = message.getMessageProperties();
        messageProperties.setDeliveryMode(PERSISTENT);
        return message;
    };

    private static final String RABBIT_MQ_ROUTING_KEY = EMAIL_MESSAGE_QUEUE_NAME;

    public void sendEmailMessage(String subject,
                                 String recipients,
                                 String bccRecipients,
                                 String sender,
                                 String content,
                                 Map<String, byte[]> attachments) throws EmailSenderClientException {
        RabbitMqMailMessage rabbitMqMailMessage = new RabbitMqMailMessage();
        rabbitMqMailMessage.setSubject(subject);
        addRecipients(recipients, rabbitMqMailMessage.getRecipients());
        addRecipients(bccRecipients, rabbitMqMailMessage.getBccRecipients());
        rabbitMqMailMessage.setSender(StringUtils.isEmpty(sender) ? sysAdminEmailAddress : sender);
        rabbitMqMailMessage.setContent(content);
        if (!CollectionUtils.isEmpty(attachments)) {
            rabbitMqMailMessage.setAttachments(attachments);
        }
        try {
            RabbitMqMailMessageValidator.validateRabbitMqMailMessage(rabbitMqMailMessage);
        } catch (EmailApiException e) {
            throw new EmailSenderClientException(e.getMessageBundleKey());
        }
        rabbitTemplate.convertAndSend(RABBIT_MQ_ROUTING_KEY, rabbitMqMailMessage, messagePostProcessor);
    }

    public void sendSimpleEmail(String recipient, String subject, String content) throws EmailSenderClientException {
        sendEmailMessage(subject, recipient, null, null, content, null);
    }

    private void addRecipients(String recipients, List<String> recipientList) {
        if (!isEmpty(recipients)) {
            String[] recipientArray = recipients.split(";");
            for (String recipient : recipientArray) {
                if (!isEmpty(recipient)) {
                    recipientList.add(recipient);
                }
            }
        }
    }

}
