package com.feikongbao.messaging.test;

import com.mixin.messaging.email.api.entity.MailEntity;
import com.mixin.messaging.email.api.exception.EmailException;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @Description 测试发送邮件
 * @Author jinjing
 * @Date 2019/6/4
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailClientConfig.class })
public class TestSendMail {

        @Autowired
        private SenderMailService senderMailService;

    /**  收件人为空 */
        @Test
        public void testToIsEmpty() throws Exception{

            MailEntity mailEntity = new MailEntity();
            mailEntity.setUserId("test_send_email_client");
            mailEntity.setFrom("test_feikongbao@163.com");
            List<String> to = new ArrayList();
            //to.add("jinjing@yodoo.net.cn");
            mailEntity.setTo(to);
            mailEntity.setCc(to);
            mailEntity.setBcc(to);
            mailEntity.setSubject("邮件主题：收件人为空");
            mailEntity.setContent("邮件内容：Test123");
            // mailEntity.setAddAttachments(getAttachment());
            // mailEntity.setEmailServiceEntity(buildMailServiceEntity());
            try {
                senderMailService.sendMail(mailEntity);
                fail("should get exception:\"messaging-email.the.person.receiving.the.email.is.empty\"");
            } catch (EmailException ex) {
                assertThat(ex.getMessage(), containsString("messaging-email.the.person.receiving.the.email.is.empty"));
            }

    }


    /**  发件人为空 */
    @Test
    public void testFromIsEmpty() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        //mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：发件人为空");
        mailEntity.setContent("邮件内容：Test123");
        // mailEntity.setAddAttachments(getAttachment());
        // mailEntity.setEmailServiceEntity(buildMailServiceEntity());
        try {
            senderMailService.sendMail(mailEntity);
            fail("should get exception: \"messaging-email.the.person.sending.the.email.is.empty\"");
        } catch (EmailException ex) {
            assertThat(ex.getMessage(), containsString("messaging-email.the.person.sending.the.email.is.empty"));
        }

    }

    /**  邮件主题为空 */
    @Test
    public void testSubjectIsEmpty() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject(null);
        mailEntity.setContent("邮件内容：测试邮件主题为空");

        try {
            senderMailService.sendMail(mailEntity);
            fail("should get exception: \"messaging-email.user.send.mail.the.subject.is.empty\"");
        } catch (EmailException ex) {
            assertEquals("messaging-email.user.send.mail.the.subject.is.empty", ex.getMessageBundleKey());

        }

    }


    /**  邮件内容为空 */
    @Test
    public void testContentIsEmpty() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件内容：测试邮件内容为空");
        mailEntity.setContent(null);

        try {
            senderMailService.sendMail(mailEntity);
            fail("should get exception: \"messaging-email.user.send.mail.the.content.is.empty\"");
        } catch (EmailException ex) {
            assertEquals("messaging-email.user.send.mail.the.content.is.empty", ex.getMessageBundleKey());

        }

    }


}
