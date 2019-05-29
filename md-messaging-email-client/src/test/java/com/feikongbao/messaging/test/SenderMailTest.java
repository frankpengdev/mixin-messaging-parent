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
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/16 15:31
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailClientConfig.class })
public class SenderMailTest {

    @Autowired
    private SenderMailService senderMailService;
    /**
     * 测试发送邮件
     * @throws Exception
     */
    @Test
    public void testMail() throws Exception{

        String from = "test_feikongbao@163.com";

        List<String> to = new ArrayList();
        to.add("877745616@qq.com");
        // to.add("460912240@qq.com");
        // to.add("receiver@jucaicat818.com");
        List<String> cc = to;
        List<String> bcc = to;
        String subject = "RabbitMQ测试发送邮件01ss";
        String content ="我们的服务面向所有的行业、规模和区域的企业。我们的核心使命是帮助客户降低差旅和采购成本，并通过持续的技术创新不断优化用户体验。我们轻便易用的云技术服务平台通过整合企业采购、差旅申请、服务预订、差旅路线管理、电子账单管理和网上支付等业务流程，帮助用户控制成本、节省时间进而使得用户工作效率得到量化式提升。";
        List<String> filePaths = new ArrayList();
        //filePaths.add("C:\\Users\\EDZ\\Desktop\\upload_test\\001.jpg");
        //filePaths.add("C:\\Users\\EDZ\\Desktop\\upload_test\\002.jpg");
        //filePaths.add("C:\\Users\\EDZ\\Desktop\\upload_test\\003.jpg");
        //filePaths.add("C:\\Users\\EDZ\\Desktop\\upload_test\\004.jpg");
        //filePaths.add("C:\\Users\\EDZ\\Desktop\\upload_test\\005.jpg");

        EmailServiceEntity emailServiceEntity = new EmailServiceEntity();
        emailServiceEntity.setUsername("test_feikongbao@163.com");
        emailServiceEntity.setPassword("feikongbao1234");
        emailServiceEntity.setHost("smtp.163.com");

        senderMailService.sendMail(from, to, cc, bcc, subject, content, filePaths,6789L, null);
    }

}
