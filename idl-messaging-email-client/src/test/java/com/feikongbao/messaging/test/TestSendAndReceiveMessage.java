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
public class TestSendAndReceiveMessage {

    @Autowired
    private SenderMailService senderMailService;

    @Autowired
    private SenderMessage<MailEntity> senderMessage;

    /** 成功发送并接收邮件 **/
    @Test
    public void sendAndReceiveMessage(){
        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("test_send_email_client");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：成功发送并接收邮件");
        mailEntity.setContent("Test case: email-client/TestSendAndReceiveMessage");

        try{
            senderMessage.sendAndReceiveMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_EMAIL,"routingKey","mailEntity","user310");
        }catch (MessagingCoreException ex){
            ex.printStackTrace();
            fail("***************  发送并接收邮件异常！ ***************");
        }
    }


    /** 邮件发送异常——exchange为空 **/
    @Test
    public void errorExchangeIsEmpty() {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("test_send_email_client");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：邮件发送异常——exchange为空");
        mailEntity.setContent("Test case: email-client/TestSendAndReceiveMessage");

        try{
            senderMessage.sendAndReceiveMessage(null,"routingKey",mailEntity,"user ABC");
            fail("Should get exception\"messaging-core.sender.exchange.empty\"");
        }catch (MessagingCoreException ex){
            ex.printStackTrace();
            assertThat(ex.getMessage(), containsString("messaging-core.sender.exchange.empty"));
        }
    }

    /** 邮件发送异常——routingKey为空 **/
    @Test
    public void errorRoutingKeyIsEmpty() {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("test_send_email_client");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：邮件发送异常——routingKey为空");
        mailEntity.setContent("Test case: email-client/TestSendAndReceiveMessage");

        try{
            senderMessage.sendAndReceiveMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_EMAIL,null,mailEntity,"user ABC");
            fail("Should get exception\"messaging-core.sender.routingKey.empty\"");
        }catch (MessagingCoreException ex){
            ex.printStackTrace();
            assertThat(ex.getMessage(), containsString("messaging-core.sender.routingKey.empty"));
        }
    }

    /** 邮件发送异常——email为空 **/
    @Test
    public void errorMessageIsEmpty() {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("test_send_email_client");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：邮件发送异常——email为空");
        mailEntity.setContent("Test case: email-client/TestSendAndReceiveMessage");

        try{
            senderMessage.sendAndReceiveMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_EMAIL,"routingKey",null,"user ABC");
            fail("Should get exception\"messaging-core.sender.body.empty\"");
        }catch (MessagingCoreException ex){
            ex.printStackTrace();
            assertThat(ex.getMessage(), containsString("messaging-core.sender.body.empty"));
        }
    }

}
