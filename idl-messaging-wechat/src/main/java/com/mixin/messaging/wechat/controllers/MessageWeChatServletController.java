package com.mixin.messaging.wechat.controllers;

import com.mixin.messaging.wechat.exception.MessageWeChatException;
import com.mixin.messaging.wechat.model.data_type.MessageWeChatResponseMessage;
import com.mixin.messaging.wechat.service.MessageWeChatHandlerAndBuildMessageAllService;
import com.mixin.messaging.wechat.service.MessageWeChatHandlerWeChatDataService;
import com.mixin.messaging.wechat.util.MessageWeChatHelpUtil;
import com.mixin.messaging.wechat.util.WeChatMessageTypeEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 微信公众号 服务器配置 认证
 *
 * @author zili.wang
 * @date 2019/5/6 10:24
 */
@Controller
@RequestMapping(value = "/WeChatServlet")
public class MessageWeChatServletController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageWeChatServletController.class);

    private static final String TOKEN = "ba41248a1db1ff1e0fb7d0591e7280be";

    @Autowired
    private MessageWeChatHandlerWeChatDataService weChatDataService;

    @Autowired
    private MessageWeChatHandlerAndBuildMessageAllService buildMessageAllService;

    /**
     * 验证消息的确来自微信服务器
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws MessageWeChatException
     */
    @RequestMapping(method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, MessageWeChatException {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echoStr = request.getParameter("echostr");

        LOGGER.info("接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature,
                timestamp, nonce, echoStr);

        String mySignature = getSHA1(TOKEN, timestamp, nonce);
        if (mySignature.equals(signature)) {
            response.getWriter().write(echoStr);
        } else {
            LOGGER.error("签名校验失败...");
        }

    }

    /**
     * 接收post请求
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws DocumentException
     */
    @RequestMapping(method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, DocumentException {

        Map<String, String> requestMap = MessageWeChatHelpUtil.xml2Map(request.getInputStream());

        String toUserName = requestMap.get("ToUserName");
        String fromUserName = requestMap.get("FromUserName");
        String createTime = requestMap.get("CreateTime");
        String msgType = requestMap.get("MsgType");
        String content = requestMap.get("Content");
        String msgId = requestMap.get("MsgID");
        String event = requestMap.get("Event");
        String eventKey = requestMap.get("EventKey");

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String openId = request.getParameter("openid");

        LOGGER.info("接收到来自用户的消息：[{}, {}, {}, {}, {}, msgId: {},{},{}]", toUserName,
                fromUserName, createTime, msgType, content, msgId, event, eventKey);

        MessageWeChatResponseMessage responseMessage = new MessageWeChatResponseMessage();
        responseMessage.setToUserName(fromUserName);
        responseMessage.setFromUserName(toUserName);

        String responseMessageString = "";

        WeChatMessageTypeEnum typeEnum = WeChatMessageTypeEnum.valueOf(WeChatMessageTypeEnum.class, msgType.toUpperCase());
        switch (typeEnum) {
            case TEXT:
                responseMessageString = buildMessageAllService.handlerTextMessage(responseMessage, openId, content);
                break;
            case EVENT:
                responseMessageString = buildMessageAllService.handlerEventMessage(responseMessage, openId, requestMap);
                break;
            default:
                break;
        }

        LOGGER.info("返给微信的报文: \n" + responseMessageString);
        response.getWriter().print(responseMessageString);

    }

    /**
     * sha1加密
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     * @throws MessageWeChatException
     */
    private String getSHA1(String token, String timestamp, String nonce) throws MessageWeChatException {
        try {
            String[] array = new String[]{token, timestamp, nonce};
            StringBuffer sb = new StringBuffer();
            Arrays.sort(array);
            for (int i = 0; i < 3; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            return DigestUtils.sha1Hex(str);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageWeChatException("messaging-wechat.sha1.validation.failed");
        }
    }
}
