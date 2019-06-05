package com.feikongbao.messaging.test;

import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.api.exception.EmailException;
import com.feikongbao.messaging.email.client.config.EmailClientConfig;
import com.feikongbao.messaging.email.client.sender.SenderMailService;
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
 * @Date 2019/6/4
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailClientConfig.class })
public class TestMailService {

    @Autowired
    private SenderMailService senderMailService;

    /**  测试指定一个正确的邮箱服务器 */
    @Test
    public void testMailService() throws Exception{

        MailEntity mailEntity = new MailEntity();
        //mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：测试指定邮箱服务器");
        //mailEntity.setContent("邮件内容：Test123");
        mailEntity.setEmailServiceEntity(buildMailServiceEntity());
        senderMailService.sendMail(mailEntity);
    }

    /**  测试指定一个错误的邮箱服务器——用户名为空 */
    @Test
    public void testMailServiceUserNameIsEmpty() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：测试指定邮箱服务器");
        mailEntity.setContent("邮件内容：Test123");
        try {
            mailEntity.setEmailServiceEntity(buildMailServiceEntityUserNameIsEmpty());
            senderMailService.sendMail(mailEntity);
            fail("should get exception: \"messaging-email.user.specified.mail.server.username.is.empty\"");
        }catch(EmailException ex){
            assertThat(ex.getMessage(), containsString("messaging-email.user.specified.mail.server.username.is.empty"));
        }

    }

    /**  测试指定一个错误的邮箱服务器——密码为空 */
    @Test
    public void testMailServicePasswordIsEmpty() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：测试指定邮箱服务器");
        mailEntity.setContent("邮件内容：Test123");
        try {
            mailEntity.setEmailServiceEntity(buildMailServiceEntityPasswordIsEmpty());
            senderMailService.sendMail(mailEntity);
            fail("should get exception: \"messaging-email.user.specified.mail.server.password.is.empty\"");
        }catch(EmailException ex){
            assertThat(ex.getMessage(), containsString("messaging-email.user.specified.mail.server.password.is.empty"));
        }

    }

    /**  测试指定一个错误的邮箱服务器——Host为空 */
    @Test
    public void testMailServiceHostIsEmpty() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：测试指定邮箱服务器");
        mailEntity.setContent("邮件内容：Test123");
        try {
            mailEntity.setEmailServiceEntity(buildMailServiceEntityHostIsEmpty());
            senderMailService.sendMail(mailEntity);
            fail("should get exception: \"messaging-email.user.specified.mail.server.address.is.empty\"");
        }catch(EmailException ex){
            assertThat(ex.getMessage(), containsString("messaging-email.user.specified.mail.server.address.is.empty"));
        }

    }

    /**  邮箱服务器 —— 正确的邮箱服务器*/
    private EmailServiceEntity buildMailServiceEntity(){
        EmailServiceEntity emailServiceEntity = new EmailServiceEntity();
        emailServiceEntity.setUsername("test_feikongbao@163.com");
        emailServiceEntity.setPassword("feikongbao1234");
        emailServiceEntity.setHost("smtp.163.com");
        emailServiceEntity.setPort(25);
        emailServiceEntity.setProtocol("SMTP");
        emailServiceEntity.setAuth(true);
        emailServiceEntity.setEncoding("UTF-8");
        return emailServiceEntity;
    }

    /**  邮箱服务器 ——没有指定Username*/
    private EmailServiceEntity buildMailServiceEntityUserNameIsEmpty(){
        EmailServiceEntity emailServiceEntity = new EmailServiceEntity();
        emailServiceEntity.setUsername(null); //未指定username
        emailServiceEntity.setPassword("feikongbao1234");
        emailServiceEntity.setHost("smtp.163.com");
        return emailServiceEntity;
    }

    /**  邮箱服务器 ——没有指定Password*/
    private EmailServiceEntity buildMailServiceEntityPasswordIsEmpty(){
        EmailServiceEntity emailServiceEntity = new EmailServiceEntity();
        emailServiceEntity.setUsername("test_feikongbao@163.com");
        emailServiceEntity.setPassword(null); //未指定password
        emailServiceEntity.setHost("smtp.163.com");
        return emailServiceEntity;
    }

    /**  邮箱服务器 ——没有指定Password*/
    private EmailServiceEntity buildMailServiceEntityHostIsEmpty(){
        EmailServiceEntity emailServiceEntity = new EmailServiceEntity();
        emailServiceEntity.setUsername("test_feikongbao@163.com");
        emailServiceEntity.setPassword("feikongbao1234");
        emailServiceEntity.setHost(null);//未指定host
        return emailServiceEntity;
    }
}
