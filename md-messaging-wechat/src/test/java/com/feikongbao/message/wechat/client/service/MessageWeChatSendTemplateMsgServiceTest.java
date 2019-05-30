package com.feikongbao.message.wechat.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feikongbao.message.wechat.client.model.entiy.message_wechat.MessageWeChatMiniProgramData;
import com.feikongbao.message.wechat.client.model.entiy.message_wechat.MessageWeChatTemplateData;
import com.feikongbao.message.wechat.client.model.entiy.message_wechat.MessageWeChatTemplateDataValue;
import com.feikongbao.message.wechat.client.model.entiy.message_wechat.MessageWeChatUserMessage;
import com.feikongbao.message.wechat.config.MessageWeChatConfiguration;
import com.feikongbao.message.wechat.util.MessageWeChatHelpUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MessageWeChatConfiguration.class)
public class MessageWeChatSendTemplateMsgServiceTest {

    @Autowired
    private MessageWeChatSendTemplateMsgService templateMsgService;

    @Test
    @SuppressWarnings("unchecked")
    public void  sendTemplateMessageTest() throws Exception {

        File file = new File(Thread.currentThread().getContextClassLoader().getResource("template.json").getFile());
        String json = new String(Files.readAllBytes(file.toPath()));

        System.out.println(json);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setLocale(Locale.SIMPLIFIED_CHINESE);
        MessageWeChatTemplateData templateData = objectMapper.readValue(json,MessageWeChatTemplateData.class);

        String flag = null;
        if(MessageWeChatHelpUtil.validatePhoneNumber(templateData.getPhoneNum().toString())){

            templateData.setTouser("oSiOu5n0qijw0pIBuTZjNrAmauSY");
            templateData.setTemplateId("H-_2o14aSsXuycYEtZQ_IicYeO2EQjH1K-QaypAdaJM");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(LocalDateTime.now().format(formatter));

            templateData.getData().get("keyword5").setValue(LocalDateTime.now().format(formatter));

            System.out.println("--------------------------------");
            System.out.println(objectMapper.writeValueAsString(templateData));
            System.out.println("--------------------------------");
            System.out.println(MessageWeChatHelpUtil.object2Json(templateData));
            System.out.println("--------------------------------");

            String msg = objectMapper.writeValueAsString(templateData);


            MessageWeChatUserMessage userMessage = new MessageWeChatUserMessage();
            userMessage.setLastUpdateTime(Instant.now());

            userMessage.setUserOpenId("oSiOu5n0qijw0pIBuTZjNrAmauSY");
            userMessage.setUserPhoneNum(templateData.getPhoneNum());

            userMessage.setUserMessageContent(msg);

            templateMsgService.sendTemplateMessage(templateData);

            flag="true";
        }

        assertNotNull(flag);

    }

}