package com.feikongbao.message.wechat.controllers;

import com.feikongbao.message.wechat.config.MessageWeChatConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = MessageWeChatConfiguration.class)
@AutoConfigureMockMvc
public class MessageWeChatServletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 微信服务器验证测试
     *
     * @throws Exception the exception
     * @author zili.wang
     * @date 2019 /05/24 13:55:12
     */
    @Test
    public void doGet() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/WeChatServlet?signature=f3e719e66eb942c412fa4b1b45bb6e8bc49c52b8&echostr=3615811614313791471&timestamp=1558493863&nonce=4274156")
                .characterEncoding(StandardCharsets.UTF_8.name())
        ).andReturn();

    }

    /**
     * 测试手机号绑定
     *
     * @throws Exception the exception
     * @author zili.wang
     * @date 2019 /05/24 14:32:38
     */
    @Test
    public void doPost() throws Exception {
        //手机用户18221509132已绑定微信公众号
        String xml = "<xml>\n" +
                "  <ToUserName><![CDATA[gh_885efa1734b6]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[oSiOu5n0qijw0pIBuTZjNrAmauSY]]></FromUserName>\n" +
                "  <CreateTime>1558494080</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[18221509132]]></Content>\n" +
                "  <MsgId></MsgId>\n" +
                "</xml>";

        mockMvc.perform(MockMvcRequestBuilders.post("/WeChatServlet?signature=7188435702c9b36e5975daeeb67e5f2763280385&timestamp=1558494080&nonce=981940637&openid=oSiOu5n0qijw0pIBuTZjNrAmauSY")
                .content(xml)
                .characterEncoding("UTF-8")).andReturn().getResponse().getContentAsString();

        //手机用户15900804993已取消微信公众号的绑定
        String xml2 = "<xml>\n" +
                "  <ToUserName><![CDATA[oSiOu5v5gHLdnph2IjASloOt7-Bk]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[oSiOu5n0qijw0pIBuTZjNrAmauSY]]></FromUserName>\n" +
                "  <CreateTime>1558494080</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[15900804993]]></Content>\n" +
                "  <MsgId></MsgId>\n" +
                "</xml>";

        mockMvc.perform(MockMvcRequestBuilders.post("/WeChatServlet?signature=7188435702c9b36e5975daeeb67e5f2763280385&timestamp=1558494080&nonce=981940637&openid=oSiOu5n0qijw0pIBuTZjNrAmauSY")
                .content(xml2)
                .characterEncoding("UTF-8")).andReturn().getResponse().getContentAsString();


    }
}