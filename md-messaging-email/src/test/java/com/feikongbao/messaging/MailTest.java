package com.feikongbao.messaging;

import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.config.EmailServiceConfig;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.sender.SenderMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailServiceConfig.class })
public class MailTest {

    @Autowired
    private SenderMessage<MailEntity> senderMessage;
    /**测试发送邮件测试*/
    @Test
    public void testMail() throws Exception{
        MailEntity entity = new MailEntity();
        // 收件人
        List<String> to = new ArrayList();
		to.add("877745616@qq.com");
        entity.setTo(to);
        // 抄送人
        // entity.setCc(to);
        // 密送人
        // entity.setBcc(to);
        // 标题
        entity.setSubject("通过RabbitMQ测试发送邮件");
        // 正文
        entity.setContent("我们的服务面向所有的行业、规模和区域的企业。我们的核心使命是帮助客户降低差旅和采购成本，并通过持续的技术创新不断优化用户体验。我们轻便易用的云技术服务平台通过整合企业采购、差旅申请、服务预订、差旅路线管理、电子账单管理和网上支付等业务流程，帮助用户控制成本、节省时间进而使得用户工作效率得到量化式提升。");
        // 附件
        // List<String> filePaths = new ArrayList();
        // filePaths.add("C:\\Users\\EDZ\\Desktop\\upload_test\\001.jpg");
        // 部分邮箱不支持发送.exe文件
        senderMessage.sendMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_EMAIL,AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_EMAIL,entity,789L);
    }
}