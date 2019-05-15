package com.feikongbao.message.wechat.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feikongbao.message.wechat.client.model.entiy.message_wechat.MessageWeChatTemplateData;
import com.feikongbao.message.wechat.client.model.entiy.message_wechat.MessageWeChatUserMessage;
import com.feikongbao.message.wechat.model.mapper.MessageWeChatUserInfoMapper;
import com.feikongbao.message.wechat.util.MessageWeChatHelpUtil;
import com.feikongbao.messaging.core.aopaspect.MessageAckAop;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.receiver.ReceiverMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zili.wang
 * @date 2019/5/8 15:38
 */
@Service
public class MessageWeChatSendMsgService implements ReceiverMessage {

    @Autowired
    private MessageWeChatSendTemplateMsgService templateMsgService;

    @Autowired
    private MessageWeChatUserInfoMapper userInfoMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = AbstractMessagingConstants.DIRECT_MQ_QUEUES_WE_CHAT, autoDelete = "false"),
            exchange = @Exchange(value = AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_WE_CHAT, type = ExchangeTypes.DIRECT),
            key = AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_WE_CHAT
    ))
    @MessageAckAop
    @RabbitHandler
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        if(message.getBody().length > 0){
            //处理消息
            ObjectMapper objectMapper = new ObjectMapper();
            MessageWeChatTemplateData templateData = objectMapper.readValue(message.getBody(), MessageWeChatTemplateData.class);

            // 查询openId
            String openId = userInfoMapper.selectOpenIdByPhoneNum(templateData.getPhoneNum());
            templateData.setTouser(openId);

            // TODO
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(LocalDateTime.now().format(formatter));
            templateData.getData().get("keyword5").setValue(LocalDateTime.now().format(formatter));

            String templateDataToString = MessageWeChatHelpUtil.object2Json(templateData);

            MessageWeChatUserMessage userMessage = new MessageWeChatUserMessage();
            userMessage.setUserOpenId(openId);
            userMessage.setUserPhoneNum(templateData.getPhoneNum());
            userMessage.setUserMessageContent(templateDataToString);

            //发送消息
            templateMsgService.sendTemplateMessage(templateDataToString,userMessage);
        }

    }
}
