package com.feikongbao.messaging.core.constants;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/9 16:18
 **/
public abstract class AbstractMessagingConstants {

    /** ############################# 共用信息 ################################################# */
    public final static Integer CODE = 400;
    public final static String EXCHANGE_EMPTY = "交换机名称为空";
    public final static String QUEUE_EMPTY = "队列为空";
    public final static String ROUTINGKEY_EMPTY = "路由键为空";
    public final static String SEND_BODY_EMPTY = "发送消息为空";


    /** ############################## topic 路由规则匹配######################################## */
    /*** topic 交换机名称 */
   public final static String TOPIC_MQ_EXCHANGE_TEST = "topic.mq.exchange.test";

    /*** topic 队列 名称 */
   public final static String TOPIC_MQ_QUEUES_TEST = "topic.mq.queues.test";

    /*** topic 路由键名称,发送方 */
    public final static String TOPIC_MQ_ROUTINGKEY_SENDER_TEST = "topic.mq.routingkey.test";

    /*** topic 订单路由键名称，接收方*/
    public final static String TOPIC_MQ_ROUTINGKEY_RECEIVER_TEST = "topic.mq.routingkey.*";

    /** ############################## direct 点对点匹配 ############################################# *
     /*** direct 邮件交换机名称 */
    public final static String DIRECT_MQ_EXCHANGE_TEST = "direct.mq.exchange.test";

    /*** direct 邮件 队列 名称 */
    public final static String DIRECT_MQ_QUEUES_TEST = "direct.mq.queues.test";

    /*** direct 邮件路由键名称，接收方*/
    public final static String DIRECT_MQ_ROUTINGKEY_TEST = "direct.mq.routingkey.test";

    /** ------------------------ 发送邮件 开始 ------------------------------------------- */
    /*** direct 邮件交换机名称 */
    public final static String DIRECT_MQ_EXCHANGE_EMAIL = "direct.mq.exchange.email";

    /*** direct 邮件 队列 名称 */
    public final static String DIRECT_MQ_QUEUES_EMAIL = "direct.mq.queues.email";

    /*** direct 邮件路由键名称，接收方*/
    public final static String DIRECT_MQ_ROUTINGKEY_EMAIL = "direct.mq.routingkey.email";
    /** ------------------------ 发送邮件 结束 ------------------------------------------- */

     /** ############################## fanout 广播发送消息 ####################################### */
    /*** fanout 交换机名称 */
    public final static String FANOUT_MQ_EXCHANGE_TEST = "fanout.mq.exchange.test";

    /*** fanout 队列 名称 */
    public final static String FANOUT_MQ_QUEUES_TEST = "fanout.mq.queues.test";

    // 微信公众号
    public final static String DIRECT_MQ_QUEUES_WE_CHAT = "direct.mq.queues.wechat";
    public final static String DIRECT_MQ_EXCHANGE_WE_CHAT = "direct.mq.exchange.wechat";
    public final static String DIRECT_MQ_ROUTINGKEY_WE_CHAT = "direct.mq.routingkey.wechat";

}
