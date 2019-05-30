package com.feikongbao.message.wechat.client.service;

import com.feikongbao.message.wechat.client.model.entiy.message_wechat.*;
import com.feikongbao.message.wechat.model.mapper.MessageWeChatUserMessageMapper;
import com.feikongbao.message.wechat.exception.MessageWeChatException;
import com.feikongbao.message.wechat.service.MessageWeChatHandlerWeChatDataService;
import com.feikongbao.message.wechat.util.MessageWeChatHelpUtil;
import com.feikongbao.message.wechat.util.MessageWeChatRequestUrlEnum;
import com.feikongbao.message.wechat.util.WeChatHttpStatusCodeEnum;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.exception.MessagingCoreException;
import com.feikongbao.messaging.core.sender.SenderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

/**
 * @author zili.wang
 * @date 2019/4/30 10:35
 */
@Service
public class MessageWeChatSendTemplateMsgService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageWeChatSendTemplateMsgService.class);

    private static final String WECHAT_TEMPLATE_REQUEST_URL = MessageWeChatRequestUrlEnum.WECHAT_TEMPLATE_URL.getDescription();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageWeChatHandlerWeChatDataService weChatDataService;

    @Autowired
    private MessageWeChatUserMessageMapper userMessageMapper;

    @Autowired
    private SenderMessage<MessageWeChatTemplateData> senderMessage;

    /**
     * 发送消息
     * url和miniprogram都是非必填字段,若都不传则模板无跳转,若都传,会优先跳转至小程序
     * 开发者可根据实际需要选择其中一种跳转方式即
     * 当用户的微信客户端版本不支持跳小程序时,将会跳转至url
     *
     * @param templateData the template data to string
     * @throws MessageWeChatException the message we chat exception
     * @author zili.wang
     * @date 2019/04/30 10:25:03
     */
    public String sendTemplateMessage(MessageWeChatTemplateData templateData) throws MessageWeChatException, MessagingCoreException {
        //获取accessToken
        String accessToken = weChatDataService.getWeChatAccessTokenFromCache();

        String templateDataToString = MessageWeChatHelpUtil.object2Json(templateData);

        ResponseEntity<MessageWeChatResponseEntity> responseEntity = restTemplate.postForEntity(WECHAT_TEMPLATE_REQUEST_URL, templateDataToString, MessageWeChatResponseEntity.class, accessToken);

        MessageWeChatResponseEntity weChatResponseEntity = responseEntity.getBody();
        LOGGER.info("发送模板消息返回的结果: ErrCode: {}, ErrMsg: {}, MsgId: {}", weChatResponseEntity.getErrCode(),
                weChatResponseEntity.getErrMsg(), weChatResponseEntity.getMsgId());

        String ERR_CODE = "0";
        if(ERR_CODE.equals(weChatResponseEntity.getErrCode())){
            return "SUCCESS";
        }else {
            StringBuffer stringBuffer = new StringBuffer("FAIL: ");
            stringBuffer.append(weChatResponseEntity.getErrMsg());
            return stringBuffer.toString();
        }

    }



}
