import com.feikongbao.message.wechat.config.MessageWeChatConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author zili.wang
 * @date 2019/5/5 13:54
 */
@EnableAutoConfiguration
@ComponentScan("com.feikongbao.message.wechat")
@EnableWebMvc
public class SpringBootStartApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootStartApplication.class);
    }

}
