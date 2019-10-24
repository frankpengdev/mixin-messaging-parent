package com.mixin.messaging.core.enums;


/**
 * @Description 消息类型判断
 * @Author jinjun_luo
 * @Date 2019/4/11 9:33
 **/
public enum MessagingEnum {

	/** 编号 **/
	APPID,

	/** 用于发送消息的用户id*/
	FEIKONGBAO_USER_ID,

	/** 用于发邮件*/
	Mq_Email,
	/** 用于发微信*/
	Mq_WeiXin,

	/** ############################# 共用信息 ################################################# */
	EXCHANGE_EMPTY,
	QUEUE_EMPTY,
	ROUTING_KEY_EMPTY,
	SEND_BODY_EMPTY,


	/** ############################## topic 路由规则匹配######################################## */
	/*** topic 订单交换机名称 */
	TOPIC_EXCHANGE_INFO_OBJ,
	TOPIC_EXCHANGE_INFO_BYTE,

	/*** topic 订单 队列 名称 */
	TOPIC_QUEUES_INFO_OBJ,
	TOPIC_QUEUES_INFO_BYTE,

	/*** topic 订单路由键名称 */
	TOPIC_ROUTING_KEY_INFO_OBJ,
	TOPIC_ROUTING_KEY_INFO_BYTE,

	/** ############################## direct 点对点匹配 ############################################# */
	/*** direct 订单交换机名称 */
	DIRECT_EXCHANGE_INFO_OBJ,
	DIRECT_EXCHANGE_INFO_BYTE,

	/*** direct 订单 队列 名称 */
	DIRECT_QUEUES_INFO_OBJ,
	DIRECT_QUEUES_INFO_BYTE,

	/*** direct 订单路由键名称 */
	DIRECT_ROUTING_KEY_INFO_OBJ,
	DIRECT_ROUTING_KEY_INFO_BYTE,

	/** ------------------------ 发送邮件 开始 ------------------------------------------- */
	/*** direct 邮件交换机名称 */
	DIRECT_EXCHANGE_INFO_MAIL,

	/*** direct 邮件 队列 名称 */
	DIRECT_QUEUES_INFO_MAIL_OBJ,

	/*** direct 邮件路由键名称,发送方 */
	DIRECT_ROUTING_KEY_INFO_MAIL_OBJ,
	/** ------------------------ 发送邮件 结束 ------------------------------------------- */

	/** ############################## fanout 广播发送消息 ####################################### */
	/*** fanout 订单交换机名称 */
	FANOUT_EXCHANGE_INFO__OBJ,
	FANOUT_EXCHANGE_INFO_BYTE,

	/*** fanout 订单 队列 名称 */
	FANOUT_QUEUES_INFO_OBJ,
	FANOUT_QUEUES_INFO_BYTE;
}
