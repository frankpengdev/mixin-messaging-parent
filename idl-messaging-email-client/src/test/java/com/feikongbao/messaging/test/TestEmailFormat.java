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
import static org.junit.Assert.assertThat;

/**
 * @Description 测试发送邮件
 * @Author jinjing
 * @Date 2019/6/5
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailClientConfig.class })
public class TestEmailFormat {

    @Autowired
    private SenderMailService senderMailService;

    /** 发件人邮箱格式异常 ——没有@ */
    @Test
    public void fromFormatError() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("abcdefghijklmn.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setCc(to);
        mailEntity.setBcc(to);
        mailEntity.setSubject("邮件主题：发件人邮箱格式异常");
        mailEntity.setContent("邮件内容：发件人邮箱：abcdefghijklmn.com" );

        try {
            senderMailService.sendMail(mailEntity);
            fail("should get exception: \"messaging-email.is.not.a.legitimate.mailbox\"");
        } catch (EmailException ex) {
            assertThat(ex.getMessage(), containsString("messaging-email.is.not.a.legitimate.mailbox"));
        }

    }

    /** 收件人邮箱格式异常 ——没有'.' */
    @Test
    public void toFormatError() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("abcdefghijklmn@com");
        mailEntity.setTo(to);
        mailEntity.setCc(to);
        mailEntity.setBcc(to);
        mailEntity.setSubject("邮件主题：收件人邮箱格式异常");
        mailEntity.setContent("邮件内容：收件人邮箱：abcdefghijklmn@com" );

        try {
            senderMailService.sendMail(mailEntity);
            fail("should get exception: \"messaging-email.is.not.a.legitimate.mailbox\"");
        } catch (EmailException ex) {
            assertThat(ex.getMessage(), containsString("messaging-email.is.not.a.legitimate.mailbox"));
        }

    }

    /** cc邮箱格式异常 ——有两个@： jinjing@yodoo@163.abc */
    @Test
    public void ccFormatError() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        List<String> cc = new ArrayList();
        cc.add("jinjing@yodoo@163.abc");
        mailEntity.setCc(cc);
        mailEntity.setSubject("邮件主题：CC邮箱格式异常");
        mailEntity.setContent("邮件内容：CC邮箱：jinjing@yodoo@163.abc" );

        try {
            senderMailService.sendMail(mailEntity);
            fail("should get exception: \"messaging-email.is.not.a.legitimate.mailbox\"");
        } catch (EmailException ex) {
            assertThat(ex.getMessage(), containsString("messaging-email.is.not.a.legitimate.mailbox"));
        }

    }

    /** bcc邮箱格式异常 ——特殊字符：  */
    @Test
    public void bccFormatError() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        List<String> bcc = new ArrayList();
        bcc.add("!#$%^&@163.com");
        mailEntity.setBcc(bcc);
        mailEntity.setSubject("邮件主题：BCC邮箱格式异常");
        mailEntity.setContent("邮件内容：BCC邮箱格式异常" );

        try {
            senderMailService.sendMail(mailEntity);
            fail("should get exception: \"messaging-email.is.not.a.legitimate.mailbox\"");
        } catch (EmailException ex) {
            assertThat(ex.getMessage(), containsString("messaging-email.is.not.a.legitimate.mailbox"));
        }

    }

}
