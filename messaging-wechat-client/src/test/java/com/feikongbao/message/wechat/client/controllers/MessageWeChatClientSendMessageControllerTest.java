package com.feikongbao.message.wechat.client.controllers;

import com.feikongbao.message.wechat.client.config.MessageWeChatClientConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = MessageWeChatClientConfiguration.class)
@AutoConfigureMockMvc
public class MessageWeChatClientSendMessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sendTemplateMessage() throws Exception {
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("template.json").getFile());
        String json = new String(Files.readAllBytes(file.toPath()));

        String rep = mockMvc.perform(MockMvcRequestBuilders.post("/wechat/send")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .characterEncoding(StandardCharsets.UTF_8.toString())
                .param("userId", "10001")
                .content(json)).andReturn().getResponse().getContentAsString();

        System.out.println(rep);

    }

    /**
     * 测试异常
     *
     * @throws Exception the exception
     * @author zili.wang
     * @date 2019/05/23 17:16:02
     */
    @Test
    public void sendTemplateMessageError() throws Exception {
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("templateError.json").getFile());
        String json = new String(Files.readAllBytes(file.toPath()));

        mockMvc.perform(MockMvcRequestBuilders.post("/wechat/send")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .characterEncoding(StandardCharsets.UTF_8.toString())
                .param("userId", "10001")
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

    }
}