import com.mixin.messaging.email.entity.RabbitMqMailMessage;
import com.mixin.messaging.executor.config.MessagingExecutorConfig;
import com.mixin.messaging.executor.listener.EmailMessageListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Peng Fan
 * @date 10/29/2019
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MessagingExecutorConfig.class })
public class EmailMessageListenerTest {

    @Autowired
    private EmailMessageListener emailMessageListener;

    @Test
    public void testSendEmail() throws IOException {
        RabbitMqMailMessage message = new RabbitMqMailMessage();
        message.setSender("dev@mail.feikongbao.cn");
        message.setSubject("this is the test email subject");
        message.setContent("this is the test email content");
        message.setRecipients(Arrays.asList("pengfan@yodoo.net.cn"));
        message.setBccRecipients(Arrays.asList("xmpengfan@gmail.com"));
        byte[] bytes = Files.readAllBytes(new File("C:\\Users\\P52\\Desktop\\优读财务费控云平台.png").toPath());
        Map<String, byte[]> attachments = new HashMap<>();
        attachments.put("test.klkl", bytes);
        message.setAttachments(attachments);
        emailMessageListener.onMailMessage(message);
    }

}
