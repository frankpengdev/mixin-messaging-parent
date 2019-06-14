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

import java.util.*;

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
    public void sendMailTest() throws Exception{
        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("test_send_email_client");
        // 发件人
        mailEntity.setFrom("test_feikongbao@163.com");
        // 接收人的邮箱
        List<String> to = new ArrayList();
        to.add("877745616@qq.com");
        to.add("jinjing@yodoo.net.cn");
        mailEntity.setTo(to);
        // 抄送
        mailEntity.setCc(Arrays.asList("geniusjj_joe@hotmail.com"));
        // 密送
        mailEntity.setBcc(Arrays.asList("geniusjj@qq.com"));
        mailEntity.setSubject("RabbitMQ测试发送邮件客户端");
        // 正文
        mailEntity.setContent("为什么要学习Java? 因为学习Java他能发邮件。。。。，别的语言也可以，但我更喜欢Java");

        // 是否需要发送附件
        mailEntity.setAddAttachments(getAttachmentBytes());

        // 是否指定邮箱服务器
        // mailEntity.setEmailServiceEntity(buildMailServiceEntity());

        senderMailService.sendMail(mailEntity);
    }

    /** bytes[] */
    public Map<String, byte[]> getAttachmentBytes(){
        Map<String, byte[]> attachmentMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("特雷莎·梅即将走人 约翰逊多管齐下争夺首相宝座，");
        stringBuilder.append("美英首脑记者会：双方期待英“脱欧”后达成双边贸易协议，");
        stringBuilder.append("美国政府与国会联手 将对科技巨头进行反垄断调查</br>");
        stringBuilder.append("韩军将领将任韩美联合演习总指挥 尝试收回战时作战指挥权，");
        stringBuilder.append("俄试射“超快速度”反导拦截弹 摄影机都没追上，");
        stringBuilder.append("坠海F-35A还没找到 日本防卫省就决定要复飞了。");
        attachmentMap.put("rabbitmq_test.txt", stringBuilder.toString().getBytes());
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