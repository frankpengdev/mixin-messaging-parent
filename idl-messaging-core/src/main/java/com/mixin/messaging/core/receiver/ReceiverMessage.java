package com.mixin.messaging.core.receiver;

import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * @Description 接收消息
 * @Author jinjun_luo
 * @Date 2019/4/9 20:36
 **/
public interface ReceiverMessage extends ChannelAwareMessageListener{

    /**
     * <pre>
     *     一、点对点接收Object
     *
     *     二、在实现类加以下注解就可以进行监听消息:第（1）个注解需修改且不限于Queue，exchange，key三个参数。第（2）个用于指定方法为监听消息的方法
     *          1、autoDelete="false"：消息持久化设置
     *          2、ExchangeTypes.DIRECT：路由器类型 direct：点对点匹配，topic：路由规则匹配，fanout：广播，headers：标题匹配，system：系统匹配
     *          3、key：路由键，根据不同的匹配规则进行设置
     *        (1)、@RabbitListener(
     *             bindings=@QueueBinding(
     *                     value=@Queue(value=AbstractMessagingConstants.DIRECT_MQ_QUEUES_ORDER_OBJ,autoDelete="false"),
     *                     exchange=@Exchange(value=AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_ORDER,type=ExchangeTypes.DIRECT),
     *                     key=AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_ORDER_RECEIVER_OBJ
     *                  )
     *              )
     *        (2)、@RabbitHandler：指定方法监听
     *        (3)、@MessageAckAop ： 用于消息处理成功后向 rabbitmq 发送确认ACK
     *
     *     三、在实现接口中的方法：public void onMessage(Message message, Channel channel)中进行业务处理
     *         (1)、参数 message ：是监听到的消息信息，转换和发送方法对应的object
     *         (2)、参数 channel ：消息通道，用于接收消息处理完业务逻辑后向rabbitmq发送确认消息
     *         (3)、channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);业务处理完成手动确认消息
     *         (4)、channel.basicAck()第二个参数 为false时表示此条消息消费成功，为true时表示所有消息消费成功
     *         (5)、message获取的消息体是byte[] 需用 ObjectMapper objectMapper = new ObjectMapper();
     *               中的方法T t = objectMapper.readValue(message.getBody(), T.class);获取对应发送进的对象
     *
     * </pre>
     * @param message
     * @param channel
     * @throws Exception
     */
    // public void onMessage(Message message, Channel channel)throws Exception;
}
