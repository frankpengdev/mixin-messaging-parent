package com.feikongbao.message.wechat.service;

import com.feikongbao.message.wechat.config.MessageWeChatConfiguration;
import com.feikongbao.message.wechat.exception.MessageWeChatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MessageWeChatConfiguration.class)
public class MessageWeChatHandlerWeChatDataServiceTest {

    @Autowired
    private MessageWeChatHandlerWeChatDataService weChatDataService;

    @Test
    public void getWeChatAccessToken() {

        try {
            String token = weChatDataService.getWeChatAccessToken();
        } catch (MessageWeChatException e) {
            e.printStackTrace();
        }

    }
}