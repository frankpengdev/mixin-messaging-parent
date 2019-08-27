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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MessageWeChatClientConfiguration.class)
public class MessageWeChatClientSendMessageServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageWeChatClientSendMessageServiceTest.class);

    @Autowired
    private MessageWeChatClientSendMessageService sendMessageService;

    /**
     * 测试之前改template.json的电话号码.
     * 正常发微信通知
     * @throws MessagingCoreException the messaging core exception
     * @throws IOException            the io exception
     * @author zili.wang
     * @date 2019 /05/28 20:52:46
     */
    @Test(expected = Exception.class)
    public void sendMessageToMq() throws MessagingCoreException, IOException {
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("template.json").getFile());
        String json = new String(Files.readAllBytes(file.toPath()));
        ObjectMapper objectMapper = new ObjectMapper();
        MessageWeChatClientTemplateData templateData = objectMapper.readValue(json, MessageWeChatClientTemplateData.class);

        ResponseData rspMsg = sendMessageService.sendMessageToMq(templateData,"1001");
        LOGGER.info("response message: " + rspMsg.getErrMsg());
    }


    /**
     * 手机号未绑定公众号
     * @throws MessagingCoreException
     * @throws IOException
     */
    @Test(expected = Exception.class)
    public void testPhoneNotBind() throws MessagingCoreException, IOException {
            File file = new File(Thread.currentThread().getContextClassLoader().getResource("phoneNotBindTemplate.json").getFile());
            String json = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper objectMapper = new ObjectMapper();
            MessageWeChatClientTemplateData templateData = objectMapper.readValue(json, MessageWeChatClientTemplateData.class);
            ResponseData rspMsg = sendMessageService.sendMessageToMq(templateData,"1001");
            assertEquals("-1", rspMsg.getErrCode());
            assertEquals("FAIL: UNBOUND TELEPHONE NUMBER" ,rspMsg.getErrMsg());

    }

    /**
     * Template_id不存在
     * @throws MessagingCoreException
     * @throws IOException
     */
    @Test(expected = Exception.class)
    public void testTemplateIdNotExist() throws MessagingCoreException, IOException {

        File file = new File(Thread.currentThread().getContextClassLoader().getResource("templateIdNotExistTemplate.json").getFile());
        String json = new String(Files.readAllBytes(file.toPath()));
        ObjectMapper objectMapper = new ObjectMapper();
        MessageWeChatClientTemplateData templateData = objectMapper.readValue(json, MessageWeChatClientTemplateData.class);
        ResponseData rspMsg = sendMessageService.sendMessageToMq(templateData,"1001");
        assertEquals("-1", rspMsg.getErrCode());
        assertEquals(rspMsg.getErrMsg().contains("FAIL: invalid template_id"), true);
    }

    /**
     * touser为空(目前user为空不会报错，返回ErrCode = 0，ErrMsg = SUCCESS )
     * @throws MessagingCoreException
     * @throws IOException
     */
    @Test(expected = Exception.class)
    public void testUserIdIsNull() throws MessagingCoreException, IOException {

        File file = new File(Thread.currentThread().getContextClassLoader().getResource("userIdIsNullTemplate.json").getFile());
        String json = new String(Files.readAllBytes(file.toPath()));
        ObjectMapper objectMapper = new ObjectMapper();
        MessageWeChatClientTemplateData templateData = objectMapper.readValue(json, MessageWeChatClientTemplateData.class);
        ResponseData rspMsg = sendMessageService.sendMessageToMq(templateData,"1001");
        assertEquals("0", rspMsg.getErrCode());
        assertEquals("SUCCESS", rspMsg.getErrMsg());
    }

    /**
     * 手机号格式错误
     * @throws MessagingCoreException
     * @throws IOException
     */
    @Test(expected = Exception.class)
    public void testPhoneNumberFormatError() throws MessagingCoreException, IOException {

        File file = new File(Thread.currentThread().getContextClassLoader().getResource("phoneNumberFormatErrorTemplate.json").getFile());
        String json = new String(Files.readAllBytes(file.toPath()));
        ObjectMapper objectMapper = new ObjectMapper();
        MessageWeChatClientTemplateData templateData = objectMapper.readValue(json, MessageWeChatClientTemplateData.class);
        ResponseData rspMsg = sendMessageService.sendMessageToMq(templateData,"1001");
        assertEquals("-1", rspMsg.getErrCode());
        assertEquals(rspMsg.getErrMsg().contains("number format is wrong"), true);
    }

}