package com.feikongbao.messaging.core.aopaspect;

import com.feikongbao.messaging.core.config.MessagingCoreConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description 邮箱服务器配置
 * @author jinjun_luo
 * @date 7/01/2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MessagingCoreConfig.class)
public @interface EnableMessagingCoreConfig {

}
