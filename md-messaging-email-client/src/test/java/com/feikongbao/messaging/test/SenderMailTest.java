package com.feikongbao.messaging.test;

import com.feikongbao.messaging.email.api.entity.EmailServiceEntity;
import com.feikongbao.messaging.email.client.config.EmailClientConfig;
import com.feikongbao.messaging.email.client.sender.SenderMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 测试发送邮件
 * @Author jinjun_luo
 * @Date 2019/4/16 15:31
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailClientConfig.class })
public class SenderMailTest {

    @Autowired
    private SenderMailService senderMailService;

    /** 测试发送邮件 **/
    @Test
    public void testMail() throws Exception{
        // 接收的邮箱
        List<String> to = new ArrayList();
        to.add("geniusjj@qq.com");
        // 抄送
        List<String> cc = to;
        // 密送
        List<String> bcc = to;
        String subject = "RabbitMQ测试发送邮件00001";
        String content ="我们的服务面向所有的行业、规模和区域的企业。我们的核心使命是帮助客户降低差旅和采购成本，并通过持续的技术创新不断优化用户体验。我们轻便易用的云技术服务平台通过整合企业采购、差旅申请、服务预订、差旅路线管理、电子账单管理和网上支付等业务流程，帮助用户控制成本、节省时间进而使得用户工作效率得到量化式提升。";
        List<String> filePaths = new ArrayList();

//        filePaths.add("D:\\test message\\JPG.jpg");
//        filePaths.add("D:\\test message\\test.txt");
//        filePaths.add("D:\\test message\\test case.xls");

        EmailServiceEntity emailServiceEntity = new EmailServiceEntity();
        emailServiceEntity.setUsername("test_feikongbao@163.com");
        emailServiceEntity.setPassword("feikongbao1234");
        emailServiceEntity.setHost("smtp.163.com");

        senderMailService.sendMail(to, cc, bcc, subject, content, filePaths,6789L, emailServiceEntity);
    }
}