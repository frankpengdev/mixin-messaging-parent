package com.feikongbao.message.wechat.client.service;

import com.feikongbao.message.wechat.client.model.entiy.MessageWeChatClientTemplateData;
import com.feikongbao.message.wechat.client.model.entiy.ResponseData;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.exception.MessagingCoreException;
import com.feikongbao.messaging.core.sender.SenderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.SmartValidator;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zili.wang
 * @date 2019/5/21 17:05
 */
@Service
public class MessageWeChatClientSendMessageService {

    @Autowired
    private SenderMessage<MessageWeChatClientTemplateData> senderMessage;

    @Autowired
    SmartValidator validator;

    /**
     * Send message to mq response data.
     * 0 成功 , -1 失败
     *
     * @param templateData the template data
     * @param userId       the user id
     * @return the response data
     * @throws MessagingCoreException the messaging core exception
     * @author zili.wang
     * @date 2019/05/30 15:00:42
     */
    public ResponseData sendMessageToMq( MessageWeChatClientTemplateData templateData,
                                        String userId) throws MessagingCoreException {
        ResponseData responseData = new ResponseData();
        //验证数据
        BindingResult bindingResult = new BeanPropertyBindingResult(templateData,templateData.getClass().getSimpleName());
        validator.validate(templateData,bindingResult);
        if(bindingResult.hasErrors()){
            responseData.setErrCode("-1");
            StringBuffer errorMsg = new StringBuffer();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            AtomicInteger atomicInteger = new AtomicInteger(1);
            errorList.stream().forEach(fieldError -> {
                errorMsg.append(atomicInteger.getAndAdd(1) + ". " + fieldError.getField() + ": " + fieldError.getDefaultMessage() + "  ");
            });
            responseData.setErrMsg(errorMsg.toString());
            return responseData;
        }


        // 等待十秒 超时返回null
        Object object = senderMessage.sendAndReceiveMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_WE_CHAT,
                AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_WE_CHAT, templateData, userId);

        if (null == object) {
            responseData.setErrCode("0");
            responseData.setErrMsg("SUCCESS");
            return responseData;
        }

        String responseMessage = new String((byte[]) object);
        String SUCCESS = "SUCCESS";
        if (responseMessage.startsWith(SUCCESS)) {
            responseData.setErrCode("0");
            responseData.setErrMsg(SUCCESS);
        } else {
            responseData.setErrCode("-1");
            responseData.setErrMsg(responseMessage);
        }
        return responseData;
    }

}
