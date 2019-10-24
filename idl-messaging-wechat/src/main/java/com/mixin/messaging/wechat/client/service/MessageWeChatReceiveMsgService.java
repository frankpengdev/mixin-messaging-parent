package com.mixin.messaging.wechat.client.service;

import com.mixin.messaging.wechat.client.model.entiy.message_wechat.MessageWeChatTemplateData;
import com.mixin.messaging.wechat.model.mapper.MessageWeChatUserInfoMapper;
import com.mixin.messaging.wechat.util.MessageWeChatHelpUtil;
import com.mixin.messaging.core.constants.AbstractMessagingConstants;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zili.wang
 * @date 2019/5/8 15:38
 */
@Service
public class MessageWeChatReceiveMsgService {

    @Autowired
    private MessageWeChatSendTemplateMsgService templateMsgService;

    @Autowired
    private MessageWeChatUserInfoMapper userInfoMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = AbstractMessagingConstants.DIRECT_MQ_QUEUES_WE_CHAT, autoDelete = "false"),
            exchange = @Exchange(value = AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_WE_CHAT, type = ExchangeTypes.DIRECT),
            key = AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_WE_CHAT
    ), concurrency = "10")
    @RabbitHandler
    public String onMessage(Message message, Channel channel) throws Exception {
        if (message.getBody().length > 0) {
            //处理消息
            MessageWeChatTemplateData templateData = MessageWeChatHelpUtil.json2Object(message.getBody(),
                    MessageWeChatTemplateData.class);

            // 查询openId
            String openId = userInfoMapper.selectOpenIdByPhoneNum(templateData.getPhoneNum());
            if (StringUtils.isEmpty(openId)) {
                return "FAIL: UNBOUND TELEPHONE NUMBER";
            }
            templateData.setTouser(openId);

            //发送消息
            return templateMsgService.sendTemplateMessage(templateData);
        }
        return "FAIL";
    }
}
