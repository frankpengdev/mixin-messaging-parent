package com.feikongbao.message.wechat.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zili.wang
 * @date 2019/5/22 17:48
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket weChatDocketApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.feikongbao.message.wechat.client"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("weChat client")
                .description("微信客户端")
                .version("1.0")
                .contact(new Contact("me",null,"wangzili@yodoo.net.cn"))
                .build();
    }

}
