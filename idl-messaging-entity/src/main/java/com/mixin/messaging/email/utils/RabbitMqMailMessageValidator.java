package com.mixin.messaging.email.utils;

import com.mixin.messaging.email.entity.RabbitMqMailMessage;
import com.mixin.messaging.email.exception.EmailApiException;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * @author Peng Fan
 * @date 10/28/2019
 */
public class RabbitMqMailMessageValidator {

    public static void validateRabbitMqMailMessage(RabbitMqMailMessage mail) throws EmailApiException {
        if (isEmpty(mail.getSubject())) {
            throw new EmailApiException("messaging.email.subject.is.required");
        }
        if (!isEmpty(mail.getSender()) && !isValidEmail(mail.getSender())) {
            throw new EmailApiException("messaging.email.sender.address.is.invalid");
        }
        List<String> recipients = mail.getRecipients();
        for (String recipient : recipients) {
            if (!isValidEmail(recipient)) {
                throw new EmailApiException("messaging.email.recipient.address.is.invalid");
            }
        }
        List<String> bccRecipients = mail.getBccRecipients();

        for (String bccRecipient : bccRecipients) {
            if (!isValidEmail(bccRecipient)) {
                throw new EmailApiException("messaging.email.bcc.recipient.address.is.invalid");
            }
        }

    }

    private static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }


}
