package com.feikongbao.messaging.core.aopaspect;

import com.feikongbao.messaging.core.enums.MessagingEnum;
import com.feikongbao.messaging.core.exception.MessagingCoreException;
import com.rabbitmq.client.Channel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

/**
 * @Description 用于消息处理成功后向 rabbitmq 发送确认，rabbitmq 把消息从队列中删除
 * @Author jinjun_luo
 * @Date 2019/4/26 13:59
 **/
@Aspect
@Component
public class ReceiverMessageAck {

    private static Logger logger = LoggerFactory.getLogger(ReceiverMessageAck.class);

    /**
     * // 统一切点,指定到类中的方法
     *  @Pointcut("execution(public * com.feikongbao.messaging.core.receiver.ReceiverMessage.onMessage(..))")
     *  @Pointcut("@annotation(com.feikongbao.messaging.core.aopaspect.MessageAckAop)")
     */
    @Pointcut("@annotation(com.feikongbao.messaging.core.aopaspect.MessageAckAop)")
    public void pointcut() {
    }

    /**
     *  @Description 后置通知 用于消息处理完成向rabbitmq发送确认
     *  前置通知 : @Before("Pointcut()")
     *  后置通知 : @After
     *  返回通知 rsult为返回内容 : @AfterReturning(value="Pointcut()",returning="result")
     *  异常通知 : @AfterThrowing(value="Pointcut()",throwing="e")
     *  环绕通知 ： @Around 参数 ProceedingJoinPoint
     * @param joinPoint
     * @return  JoinPoint
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object aroundAckAop(ProceedingJoinPoint joinPoint) throws Throwable {
        String userId = "";
        String uuid = "";
         try {
            // 获取方法的两个参数 ： Message, Channel
            Object[] args = joinPoint.getArgs();
            Message message = (Message)args[0];
            MessageProperties messageProperties = message.getMessageProperties();
            uuid = (String)messageProperties.getHeaders().get("spring_returned_message_correlation");
            userId = (String)messageProperties.getHeaders().get(MessagingEnum.FEIKONGBAO_USER_ID.name());
            Channel channel = (Channel)args[1];
            // 执行方法中
            Object object = joinPoint.proceed();
            // 执行方法成功后向 rabbitmq 发送确认，rabbitmq 把消息从队列中删除
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            logger.info("消息处理成功 userid:{}, messageUuid;{}", userId, uuid);
            return object;
         }catch (Exception e){
             throw new MessagingCoreException("userId: (0)  messageUuid: (1) RabbitMQ message processing success returns confirmation ACK failure",userId.toString(), uuid);
         }
    }
}
