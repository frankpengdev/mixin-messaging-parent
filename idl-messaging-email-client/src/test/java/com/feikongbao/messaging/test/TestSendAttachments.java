package com.feikongbao.messaging.test;

import com.mixin.messaging.email.api.entity.MailEntity;
import com.mixin.messaging.email.client.config.EmailClientConfig;
import com.mixin.messaging.email.client.sender.SenderMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @Description 测试发送附件
 *  @Author jinjing
 *  @Date 2019/7/8
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailClientConfig.class })
public class TestSendAttachments {

    @Autowired
    private SenderMailService senderMailService;

    /**  测试发送附件ABC (附件类型不在MiMeTypeEnum中)*/
    @Test
    public void attachmentTypeNotExist() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：发送附件rabbitmq_test.ABC");
        mailEntity.setContent("邮件内容：测试messaging.email.client/TestSendAttachments ");

        Map<String, byte[]> attachmentMap = new HashMap<>();
        StringBuilder strbur = new StringBuilder();
        strbur.append("邮件测试：发送附件为ABC的邮件。");
        attachmentMap.put("rabbitmq_test.ABC",strbur.toString().getBytes());
        mailEntity.setAddAttachments(attachmentMap);
        senderMailService.sendMail(mailEntity);

    }

    /**  测试发送附件TXT (附件类型在MiMeTypeEnum中)*/
    @Test
    public void sendOneAttachment() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：发送附件test.TXT");
        mailEntity.setContent("邮件内容：测试messaging.email.client/TestSendAttachments ");

        Map<String, byte[]> attachmentMap = new HashMap<>();
        StringBuilder strbur = new StringBuilder();
        strbur.append("邮件测试：发送附件为ABC的邮件。");
        attachmentMap.put("test.TXT",strbur.toString().getBytes());
        mailEntity.setAddAttachments(attachmentMap);
        senderMailService.sendMail(mailEntity);

    }

    /**  测试发送5个附件类型 (附件类型在MiMeTypeEnum中)*/
    @Test
    public void send5Attachments() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：发送5个附件(附件类型在MiMeTypeEnum中");
        mailEntity.setContent("邮件内容：测试messaging.email.client/TestSendAttachments");

        Map<String, byte[]> attachmentMap = new HashMap<>();
        StringBuilder strbur = new StringBuilder();
        strbur.append("邮件测试：发送附件为ABC的邮件。");
        attachmentMap.put("test1.TXT",strbur.toString().getBytes());
        attachmentMap.put("test2.JPG",strbur.toString().getBytes());
        attachmentMap.put("test3.vcd",strbur.toString().getBytes());
        attachmentMap.put("test4.xlsm",strbur.toString().getBytes());
        attachmentMap.put("test5.exe",strbur.toString().getBytes());
        mailEntity.setAddAttachments(attachmentMap);
        senderMailService.sendMail(mailEntity);

    }


    /**  测试发送10个附件类型 (部分附件类型在MiMeTypeEnum中)*/
    @Test
    public void send10Attachments() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("user310");
        mailEntity.setFrom("test_feikongbao@163.com");
        List<String> to = new ArrayList();
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        mailEntity.setSubject("邮件主题：测试发送10个附件类型 (部分附件类型在MiMeTypeEnum中)");
        mailEntity.setContent("邮件内容：测试messaging.email.client/TestSendAttachments");

        Map<String, byte[]> attachmentMap = new HashMap<>();
        StringBuilder strbur = new StringBuilder();
        strbur.append("邮件测试：测试发送10个附件类型 (部分附件类型在MiMeTypeEnum中)。");
        attachmentMap.put("test1.sdd",strbur.toString().getBytes());
        attachmentMap.put("test2.png",strbur.toString().getBytes());
        attachmentMap.put("test3.html",strbur.toString().getBytes());
        attachmentMap.put("test4.gif",strbur.toString().getBytes());
        attachmentMap.put("test5.mp3",strbur.toString().getBytes());
        attachmentMap.put("test6.AAAA",strbur.toString().getBytes());
        attachmentMap.put("test7.BBBB",strbur.toString().getBytes());
        attachmentMap.put("test8.CCCC",strbur.toString().getBytes());
        attachmentMap.put("test9.DDDD",strbur.toString().getBytes());
        attachmentMap.put("test10.EEEE",strbur.toString().getBytes());

        mailEntity.setAddAttachments(attachmentMap);
        senderMailService.sendMail(mailEntity);

    }


}
