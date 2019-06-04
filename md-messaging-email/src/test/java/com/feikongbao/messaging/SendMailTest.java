package com.feikongbao.messaging;

import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.config.EmailServiceConfig;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.sender.SenderMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailServiceConfig.class })
public class SendMailTest {

    @Autowired
    private SenderMessage<MailEntity> senderMessage;

    /**测试发送邮件测试*/
    @Test
    public void testSendMail() throws Exception{

        MailEntity mailEntity = new MailEntity();
        mailEntity.setUserId("test_send_email");
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
        mailEntity.setSubject("RabbitMQ测试发送邮件服务端");
        // 正文
        mailEntity.setContent("为什么要学习Java? 因为学习Java他能发邮件。。。。，别的语言也可以，但我更喜欢Java");

        // 是否需要发送附件
        // mailEntity.setAddAttachments(getAttachment());

        // 是否指定邮箱服务器
        // mailEntity.setEmailServiceEntity(buildMailServiceEntity());
        // 部分邮箱不支持发送.exe文件
        senderMessage.sendMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_EMAIL,AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_EMAIL,mailEntity,"test_send_email");
    }

    /** 附件: 如果测试需要发送附件， **/
    public Map<String, byte[]> getAttachment() throws Exception{
        Map<String, byte[]> attachmentMap = new HashMap<>();
        String[] strings = new String[]{"001.jpg","002.jpg","003.jpg"};
        for (int i = 0; i< strings.length; i++){
            File file = new File("C:\\Users\\EDZ\\Desktop\\upload_test\\" + strings[i]);
            InputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes,0,inputStream.available());
            attachmentMap.put(strings[i], bytes);
            if (inputStream != null){
                inputStream.close();
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