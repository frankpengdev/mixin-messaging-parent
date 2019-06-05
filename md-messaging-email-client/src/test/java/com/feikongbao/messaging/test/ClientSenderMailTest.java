package com.feikongbao.messaging.test;

import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.client.config.EmailClientConfig;
import com.feikongbao.messaging.email.client.sender.SenderMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 测试发送邮件
 * @Author jinjun_luo
 * @Date 2019/4/16 15:31
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailClientConfig.class })
public class ClientSenderMailTest {

    @Autowired
    private SenderMailService senderMailService;

    /** 测试发送邮件 **/
    @Test
    public void sendMailtest() throws Exception{
        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("test_send_email_client");
        // 发件人
        mailEntity.setFrom("test_feikongbao@163.com");
        // 接收人的邮箱
        List<String> to = new ArrayList();
        to.add("877745616@qq.com");
        mailEntity.setTo(to);
        // 抄送
        mailEntity.setCc(to);
        // 密送
        mailEntity.setBcc(to);
        mailEntity.setSubject("RabbitMQ测试发送邮件客户端");
        // 正文
        mailEntity.setContent("为什么要学习Java? 因为学习Java他能发邮件。。。。，别的语言也可以，但我更喜欢Java");

        // 是否需要发送附件
        // mailEntity.setAddAttachments(getAttachment());

        // 是否指定邮箱服务器
        // mailEntity.setEmailServiceEntity(buildMailServiceEntity());

        senderMailService.sendMail(mailEntity);
    }

    /** 附件: 如果测试需要发送附件， **/
    public Map<String, byte[]> getAttachment() throws Exception{
        Map<String, byte[]> attachmentMap = new HashMap<>();
        String[] strings = new String[]{"001.jpg","002.jpg","003.jpg", "test.txt"};
        for (int i = 0; i< strings.length; i++){
            File file = new File("C:\\Users\\EDZ\\Desktop\\upload_test\\" + strings[i]);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(bytes,0,bufferedInputStream.available());
            attachmentMap.put(strings[i], bytes);
            if (bufferedInputStream != null){
                bufferedInputStream.close();
            }
        }
        return attachmentMap;
    }

    /** 邮箱服务器 **/
    private EmailServiceEntity buildMailServiceEntity(){
        EmailServiceEntity emailServiceEntity = new EmailServiceEntity();
        emailServiceEntity.setUsername("test_feikongbao@163.com");
        emailServiceEntity.setPassword("feikongbao1234");
        emailServiceEntity.setHost("smtp.163.com");
        return emailServiceEntity;
    }
}