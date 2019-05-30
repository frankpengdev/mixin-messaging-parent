package com.feikongbao.message.wechat.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feikongbao.message.wechat.client.config.MessageWeChatClientConfiguration;
import com.feikongbao.message.wechat.client.model.entiy.MessageWeChatClientTemplateData;
import com.feikongbao.message.wechat.client.model.entiy.ResponseData;
import com.feikongbao.messaging.core.exception.MessagingCoreException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MessageWeChatClientConfiguration.class)
public class MessageWeChatClientSendMessageServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageWeChatClientSendMessageServiceTest.class);

    @Autowired
    private MessageWeChatClientSendMessageService sendMessageService;

    /**
     * 测试之前改template.json的电话号码.
     *
     * @throws MessagingCoreException the messaging core exception
     * @throws IOException            the io exception
     * @author zili.wang
     * @date 2019 /05/28 20:52:46
     */
    @Test
    public void sendMessageToMq() throws MessagingCoreException, IOException {
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("template.json").getFile());
        String json = new String(Files.readAllBytes(file.toPath()));
        ObjectMapper objectMapper = new ObjectMapper();
        MessageWeChatClientTemplateData templateData = objectMapper.readValue(json, MessageWeChatClientTemplateData.class);

        ResponseData rspMsg = sendMessageService.sendMessageToMq(templateData,1001L);
        LOGGER.info("response message: " + rspMsg.getErrMsg());
    }
}