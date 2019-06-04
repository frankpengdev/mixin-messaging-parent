package com.feikongbao.messaging.test;

import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.api.exception.EmailException;
import com.feikongbao.messaging.email.client.sender.SenderMailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class TestSendMail {

    public class SenderMailTest {

        @Autowired
        private SenderMailService senderMailService;
        /**
         * 测试发送邮件
         * @throws Exception
         */

        @Test
        public void testMail() throws Exception{

            MailEntity mailEntity = new MailEntity();
            mailEntity.setUserId("test_send_email_client");
            // 发件人
            mailEntity.setFrom("test_feikongbao@163.com");
            // 接收人的邮箱
            List<String> to = new ArrayList();
            //to.add("geniusjj@qq.com");
            mailEntity.setTo(to);
            // 抄送
            mailEntity.setCc(to);
            // 密送
            mailEntity.setBcc(to);
            mailEntity.setSubject("邮件主题：收件人为空");
            // 正文
            mailEntity.setContent("邮件内容：Test123");

            // 是否需要发送附件
            // mailEntity.setAddAttachments(getAttachment());

            // 是否指定邮箱服务器
            // mailEntity.setEmailServiceEntity(buildMailServiceEntity());
            try {

                senderMailService.sendMail(mailEntity);
                fail("should get exception:business-object.default.language.must.be.set ");
            } catch (EmailException ex) {
                assertThat(ex.getMessage(), containsString("messaging-email.the.person.receiving.the.email.is.empty"));
            }



        }

    }



}
