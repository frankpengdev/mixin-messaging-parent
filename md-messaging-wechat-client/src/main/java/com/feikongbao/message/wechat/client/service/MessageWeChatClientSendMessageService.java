package com.feikongbao.message.wechat.client.service;

import com.feikongbao.message.wechat.client.model.entiy.MessageWeChatClientTemplateData;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.exception.MessagingCoreException;
import com.feikongbao.messaging.core.sender.SenderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zili.wang
 * @date 2019/5/21 17:05
 */
@Service
public class MessageWeChatClientSendMessageService {

    @Autowired
    private SenderMessage<MessageWeChatClientTemplateData> senderMessage;

    public void sendMessageToMq(MessageWeChatClientTemplateData templateData, Long userId) throws MessagingCoreException {
        senderMessage.sendMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_WE_CHAT,
                AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_WE_CHAT, templateData, userId);

    }

}
