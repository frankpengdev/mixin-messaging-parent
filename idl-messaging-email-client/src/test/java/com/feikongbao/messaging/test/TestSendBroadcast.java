package com.feikongbao.messaging.test;

import com.mixin.messaging.core.constants.AbstractMessagingConstants;
import com.mixin.messaging.core.exception.MessagingCoreException;
import com.mixin.messaging.core.sender.SenderMessage;
import com.mixin.messaging.email.api.entity.MailEntity;
import com.mixin.messaging.email.client.config.EmailClientConfig;
import com.mixin.messaging.email.client.sender.SenderMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * @Description 测试发送广播
 * @Author jinjing
 * @Date 2019/6/6
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailClientConfig.class })
public class TestSendBroadcast {

    @Autowired
    private SenderMailService senderMailService;

    @Autowired
    private SenderMessage<MailEntity> senderMessage;

    /** 发送广播 -- 正确发送广播 **/
    @Test
    public void sendBroadcast(){
        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("test_send_email_client");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：发送广播");
        mailEntity.setContent("邮件内容：发送广播");

        try{
            senderMessage.broadcastMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_EMAIL,mailEntity,"test_send_email");
        }catch (MessagingCoreException ex){
            ex.printStackTrace();
            fail("***************  发送广播异常！ ***************");
        }
    }


    /** 发送广播：Exchange为空 ——抛异常 **/
    @Test
    public void sendBroadcastErrorExchangeIsEmpty() {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("test_send_email_client");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：发送广播异常——exchange为空");
        mailEntity.setContent("邮件内容：发送广播异常——exchange为空");

        try{
            senderMessage.broadcastMessage(null,mailEntity,"user ABC");
            fail("Should get exception\"messaging-core.sender.exchange.empty\"");
        }catch (MessagingCoreException ex){
            ex.printStackTrace();
            assertThat(ex.getMessage(), containsString("messaging-core.sender.exchange.empty"));
        }
    }


    /** 发送广播：message为空 ——抛异常 **/
    @Test
    public void sendBroadcastErrorMessageIsEmpty() {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("test_send_email_client");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：发送广播异常——message为空");
        mailEntity.setContent("邮件内容：发送广播异常——message为空");

        try{
            senderMessage.broadcastMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_EMAIL,null,null);
            fail("Should get exception\"messaging-core.sender.body.empty\"");
        }catch (MessagingCoreException ex){
            ex.printStackTrace();
            assertThat(ex.getMessage(), containsString("messaging-core.sender.body.empty"));
        }
    }
}
