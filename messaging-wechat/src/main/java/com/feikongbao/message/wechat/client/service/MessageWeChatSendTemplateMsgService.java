package com.feikongbao.message.wechat.client.service;

import com.feikongbao.message.wechat.client.model.entiy.message_wechat.*;
import com.feikongbao.message.wechat.model.mapper.MessageWeChatUserMessageMapper;
import com.feikongbao.message.wechat.exception.MessageWeChatException;
import com.feikongbao.message.wechat.service.MessageWeChatHandlerWeChatDataService;
import com.feikongbao.message.wechat.util.MessageWeChatRequestUrlEnum;
import com.feikongbao.message.wechat.util.WeChatHttpStatusCodeEnum;
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

    /**
     * 发送消息
     * url和miniprogram都是非必填字段,若都不传则模板无跳转,若都传,会优先跳转至小程序
     * 开发者可根据实际需要选择其中一种跳转方式即
     * 当用户的微信客户端版本不支持跳小程序时,将会跳转至url
     *
     * @param templateDataToString the template data to string
     * @throws MessageWeChatException the message we chat exception
     * @author zili.wang
     * @date 2019/04/30 10:25:03
     */
    public void sendTemplateMessage(String templateDataToString, MessageWeChatUserMessage userMessage) throws MessageWeChatException {
        //获取accessToken
        String accessToken = weChatDataService.getWeChatAccessTokenFromCache();

        ResponseEntity<MessageWeChatResponseEntity> responseEntity = restTemplate.postForEntity(WECHAT_TEMPLATE_REQUEST_URL, templateDataToString, MessageWeChatResponseEntity.class, accessToken);

        MessageWeChatResponseEntity weChatResponseEntity = responseEntity.getBody();
        LOGGER.info("发送模板消息返回的结果: ErrCode: {}, ErrMsg: {}, MsgId: {}", weChatResponseEntity.getErrCode(),
                weChatResponseEntity.getErrMsg(), weChatResponseEntity.getMsgId());

        userMessage.setLastUpdateTime(Instant.now());

        // 发送成功 0
        if ((WeChatHttpStatusCodeEnum.OK.getCode().toString()).equals(weChatResponseEntity.getErrCode())) {
            //更改状态为已发送
            userMessage.setUserMessageStatus("SEND");
            userMessage.setUserMessageMsgId(weChatResponseEntity.getMsgId());
        } else {
            // 没成功的 定时计划在推送
            userMessage.setUserMessageStatus("FAIL");
            userMessage.setErrMessage(weChatResponseEntity.getErrMsg());
        }

        userMessageMapper.insert(userMessage);

    }

}
