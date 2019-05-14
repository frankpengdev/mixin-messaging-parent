package com.feikongbao.messaging.core.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feikongbao.messaging.core.enums.MessagingEnum;
import com.feikongbao.messaging.core.exception.MessagingCoreException;
import com.feikongbao.messaging.core.service.ReturnedMessageStorageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/11 16:23
 **/
@Service
public class SenderMessage<T> implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback,InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(SenderMessage.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${mq.confirm.retry.count}")
    private int mqConfirmRetryCount;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReturnedMessageStorageService returnedMessageStorageService;

    /**
     * @Description 向消息队列发送消息,广播方式发送
     * @param exchange 交换器名称
     * @param message 消息体
     * @throws MessagingCoreException
     */
    public void broadcastMessage(String exchange, Object message,Long userId) throws MessagingCoreException {
        validationParameters(exchange, message);
        doSendMessage(exchange,"",message, userId);
    }

    /**
     * @Description 向消息队列发送消息：路由方式发送
     * @param exchange 交换器名称
     * @param routingKey 路由键
     * @param message 消息体
     * @throws MessagingCoreException
     */
    public void sendMessage(String exchange, String routingKey,Object message, Long userId) throws MessagingCoreException {
        validationParameters(exchange, routingKey, message);
        doSendMessage(exchange, routingKey, message, userId);
    }

    /**
     * 参数校验
     * @param exchange
     * @param routingKey
     * @param message
     * @throws MessagingCoreException
     */
    private void validationParameters(String exchange, String routingKey, Object message) throws MessagingCoreException{
        if (StringUtils.isBlank(routingKey)) {
            throw new MessagingCoreException("messaging-core.sender.routingKey.empty");
        }
        validationParameters(exchange,message);
    }

    /**
     * 参数校验
     * @param exchange
     * @param message
     * @throws MessagingCoreException
     */
    private void validationParameters(String exchange, Object message) throws MessagingCoreException{
        if (StringUtils.isBlank(exchange)) {
            throw new MessagingCoreException("messaging-core.sender.exchange.empty");
        }
        if (message == null){
            throw new MessagingCoreException("messaging-core.sender.body.empty");
        }
    }

    /**
     * 添加发送消息所需要的参数
     * @param exchange
     * @param routingKey
     * @param messagePayload
     * @throws MessagingCoreException
     */
    private void doSendMessage(String exchange, String routingKey, Object messagePayload, Long userId) {
        CallbackCorrelationData callbackCorrelationData = buildCorrelationData(messagePayload, exchange, routingKey, userId);
        Map<String, Object> customMessageProperties = new HashMap<>();
        customMessageProperties.put(MessagingEnum.FEIKONGBAO_USER_ID.name(), userId);
        MessagePostProcessor messagePostProcessor = new FeikongbaoMessagePostProcessor(customMessageProperties);
        rabbitTemplate.convertAndSend(exchange, routingKey, messagePayload, messagePostProcessor, callbackCorrelationData);
    }

    /**
     * 消息相关数据（消息ID）
     * @param message
     * @return
     */
    private CallbackCorrelationData buildCorrelationData(Object message, String exchange, String routingKey, Long userId) {
        CallbackCorrelationData callbackCorrelationData = new CallbackCorrelationData(UUID.randomUUID().toString().replace("-", ""), message);
        callbackCorrelationData.setExchange(exchange);
        callbackCorrelationData.setRoutingKey(routingKey);
        callbackCorrelationData.setUserId(userId);
        return callbackCorrelationData;
    }

    /**
     *  1、如果消息没有到exchange,则confirm回调,ack=false
     *  2、如果消息到达exchange,则confirm回调,ack=true
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack && correlationData instanceof CallbackCorrelationData) {
            CallbackCorrelationData callbackCorrelationData = (CallbackCorrelationData) correlationData;
            //消息发送失败,就进行重试，重试过后还不能成功就记录到数据库
            T obj = (T)callbackCorrelationData.getMessage();
            if (callbackCorrelationData.getRetryCount() < mqConfirmRetryCount) {
                // 重试次数 + 1
                callbackCorrelationData.setRetryCount(callbackCorrelationData.getRetryCount() + 1);
                // 重发发消息
                Map<String, Object> customMessageProperties = new HashMap<>();
                customMessageProperties.put(MessagingEnum.FEIKONGBAO_USER_ID.name(), callbackCorrelationData.getUserId());
                MessagePostProcessor messagePostProcessor = new FeikongbaoMessagePostProcessor(customMessageProperties);
                rabbitTemplate.convertAndSend(callbackCorrelationData.getExchange(), callbackCorrelationData.getRoutingKey(),
                                                callbackCorrelationData.getMessage(), messagePostProcessor, callbackCorrelationData);
                logger.info("MQ消息发送失败，消息重发，消息ID：{}，重发次数：{}，消息体:{}", callbackCorrelationData.getId(), callbackCorrelationData.getRetryCount(), obj);
            } else {
                String objJson = null;
                try {
                    objJson = objectMapper.writeValueAsString(obj);
                }catch (Exception e){
                    logger.error("confirm object转json 异常");
                }
                 returnedMessageStorageService.saveReturnedMessageStorage(((CallbackCorrelationData) correlationData).getUserId(),
                                                                            callbackCorrelationData.getId(), callbackCorrelationData.getExchange(),
                                                                            callbackCorrelationData.getRoutingKey(),0, cause, objJson);
                logger.info("MQ消息重发失败，消息入库，消息ID：{}，消息体:{}", correlationData.getId(), obj);
            }
        } else {
            logger.info("消息发送成功,消息ID:{}", correlationData.getId());
        }
    }

    /**
     *  1、exchange到queue成功,则不回调return
     *  2、exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        Long userId = null;
        String uuid = null;
        String objJson = null;
        try {
            Map<String, Object> headers = message.getMessageProperties().getHeaders();
            // userId
            userId = (Long) headers.get(MessagingEnum.FEIKONGBAO_USER_ID.name());
            // uuid
            uuid = (String) headers.get("spring_returned_message_correlation");
            // body
            objJson = objectMapper.writeValueAsString((T)objectMapper.readValue(message.getBody(), Object.class));
            // 保存到数据库
            returnedMessageStorageService.saveReturnedMessageStorage(userId, uuid, exchange, routingKey, replyCode, replyText, objJson);
            System.out.println("returnMessage 消息从交换器发送到队列失败:" + objJson + ",replyCode:" + replyCode + ",replyText:"
                                + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
        }catch (Exception e){
            // TODO 日志
            logger.error("returnMessage 消息从交换器发送到队列失败 userId{}, uuid{}, exchange{}, routingKey{}, " +
                        "replyCode{}, replyText{}, objJson{}",userId, uuid, exchange, routingKey, replyCode, replyText, objJson);
        }
    }

    /**
     * 原路返回
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }
}