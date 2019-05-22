package com.feikongbao.message.wechat.client.controllers;

import com.feikongbao.message.wechat.client.model.entiy.MessageWeChatClientTemplateData;
import com.feikongbao.message.wechat.client.model.entiy.ResponseData;
import com.feikongbao.message.wechat.client.service.MessageWeChatClientSendMessageService;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.exception.MessagingCoreException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zili.wang
 * @date 2019/5/20 14:39
 */
@Controller
public class MessageWeChatClientSendMessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageWeChatClientSendMessageController.class);

    @Autowired
    private MessageWeChatClientSendMessageService sendMessageService;

    @RequestMapping(value = "/wechat/send",method = RequestMethod.POST,params = {"userId"},
            consumes = "application/json")
    @ResponseBody
    public ResponseData sendTemplateMessage(HttpServletRequest request, @RequestBody MessageWeChatClientTemplateData templateData,
                                            HttpServletResponse response) {
        ResponseData responseData = new ResponseData();

        String userId = request.getParameter("userId");
        if (!StringUtils.isBlank(userId)) {
            Long userIdL = Long.parseLong(userId);
            try {
                sendMessageService.sendMessageToMq(templateData,userIdL);
                responseData.setErrCode("OK");
                responseData.setErrMsg("SUCCESS");
                return responseData;
            } catch (MessagingCoreException e) {
                LOGGER.error("MessageWeChatClientSendMessageController: err message: "+e.getMessageBundleKey());
            }
        }
        responseData.setErrCode("-1");
        responseData.setErrMsg("System is busy. should try again later");
        return responseData;

    }

}
