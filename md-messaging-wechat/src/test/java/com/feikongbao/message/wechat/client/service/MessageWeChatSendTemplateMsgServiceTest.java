package com.feikongbao.message.wechat.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feikongbao.message.wechat.client.model.entiy.message_wechat.MessageWeChatTemplateData;
import com.feikongbao.message.wechat.config.MessageWeChatConfiguration;
import com.feikongbao.message.wechat.util.MessageWeChatHelpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


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

        if(MessageWeChatHelpUtil.validatePhoneNumber(templateData.getPhoneNum().toString())){

            //测试需写入自己的openID
            templateData.setTouser("");
            templateData.setTemplateId("H-_2o14aSsXuycYEtZQ_IicYeO2EQjH1K-QaypAdaJM");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(LocalDateTime.now().format(formatter));

            templateData.getData().get("keyword5").setValue(LocalDateTime.now().format(formatter));

            templateMsgService.sendTemplateMessage(templateData);
        }

    }

}