import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author zili.wang
 * @date 2019/5/5
 */
@EnableAutoConfiguration
@ComponentScan("com.mixin.messaging.wechat")
@EnableWebMvc
public class SpringBootStartApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootStartApplication.class);
    }

}
