package com.feikongbao.messaging.core.sender;

import com.feikongbao.messaging.core.enums.MessagingEnum;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/28 19:59
 **/
public class FeikongbaoMessagePostProcessor implements MessagePostProcessor {

    private Map<String, Object> customMessageProperties = new HashMap<>();

    public FeikongbaoMessagePostProcessor(Map<String, Object> customMessageProperties) {
        this.customMessageProperties = customMessageProperties;
    }

    public Map<String, Object> getCustomMessageProperties() {
        return customMessageProperties;
    }

    public void setCustomMessageProperties(Map<String, Object> customMessageProperties) {
        this.customMessageProperties = customMessageProperties;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        MessageProperties messageProperties = message.getMessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        if (customMessageProperties.containsKey(MessagingEnum.FEIKONGBAO_USER_ID.name())) {
            messageProperties.getHeaders().put(MessagingEnum.FEIKONGBAO_USER_ID.name(),customMessageProperties.get(MessagingEnum.FEIKONGBAO_USER_ID.name()));
        } else if (customMessageProperties.containsKey("appId")) {
            message.getMessageProperties().setAppId(customMessageProperties.get("appId").toString());
        }
        return message;
    }
}
