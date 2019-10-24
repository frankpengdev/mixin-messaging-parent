package com.mixin.messaging.core.aopaspect;


import java.lang.annotation.*;

/**
 * @Description 用于消息处理成功后向 rabbitmq 发送确认，rabbitmq 把消息从队列中删除
 * <p>
 *     @Target(ElementType.METHOD) // 用在方法上
 *     @Retention(RetentionPolicy.RUNTIME) // 在运行时有效（即运行时保留）
 *     @Documented // 可以被例如javadoc此类的工具文档化
 *     @Inherited // 子类可以继承父类上的注解
 * </p>
 * @Author jinjun_luo
 * @Date 2019/4/26 13:59
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MessageAckAop {

}
