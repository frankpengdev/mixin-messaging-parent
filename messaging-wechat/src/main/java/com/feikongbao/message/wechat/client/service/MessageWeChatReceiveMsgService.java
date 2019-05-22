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

/**
 * @author zili.wang
 * @date 2019/5/8 15:38
 */
@Service
public class MessageWeChatReceiveMsgService implements ReceiverMessage {

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

            MessageWeChatUserMessage userMessage = new MessageWeChatUserMessage();
            userMessage.setUserOpenId(openId);
            userMessage.setUserPhoneNum(templateData.getPhoneNum());

            //发送消息
            templateMsgService.sendTemplateMessage(templateData,userMessage);
        }
    }
}
