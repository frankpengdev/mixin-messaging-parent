import com.mixin.messaging.email.client.config.EmailMessagingClientConfig;
import com.mixin.messaging.email.client.exception.EmailSenderClientException;
import com.mixin.messaging.email.client.sender.EmailMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Peng Fan
 * @date 10/28/2019
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmailMessagingClientConfig.class })
public class EmailMessageClientTest {

    @Autowired
    private EmailMessageSender emailMessageSender;

    @Test
    public void testSendEmailMessage() throws EmailSenderClientException {
        emailMessageSender.sendSimpleEmail("pengfan@yodoo.net.cn", "test subject", "test content");
    }

}
