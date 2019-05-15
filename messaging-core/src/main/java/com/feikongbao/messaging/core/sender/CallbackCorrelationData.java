package com.feikongbao.messaging.core.sender;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * @Description 封装消息发送到 exchange 失败回调或消息从exchange到Queues失败回调实体
 * @Author jinjun_luo
 * @Date 2019/4/22 14:06
 **/
public class CallbackCorrelationData extends CorrelationData {

    /** 消息体*/
    private volatile Object message;

    /** 交换机名称*/
    private String exchange;

    /** 路由key*/
    private String routingKey;

    /** 重试次数*/
    private int retryCount = 0;

    /*** 用户id */
    private Long userId;

    public CallbackCorrelationData() {
        super();
    }

    public CallbackCorrelationData(String id) {
        super(id);
    }

    public CallbackCorrelationData(String id, Object data) {
        this(id);
        this.message = data;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
