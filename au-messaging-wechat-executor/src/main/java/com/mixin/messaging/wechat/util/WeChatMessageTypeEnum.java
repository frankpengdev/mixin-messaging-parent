package com.mixin.messaging.wechat.util;

/**
 * The enum We chat message type.
 *
 * @author zili.wang
 */
public enum WeChatMessageTypeEnum {

    /**
     * 文本消息
     */
    TEXT,

    /**
     * 语音消息
     */
    VOICE,

    /**
     * 视频消息
     */
    VIDEO,

    /**
     * 小视频消息.
     */
    SHORTVIDEO,

    /**
     * 地理位置消息
     */
    LOCATION,

    /**
     * 链接消息
     */
    LINK,

    /**
     * 图片消息
     */
    IMAGE,

    /**
     * 事件消息
     */
    EVENT,

    /**
     * 关注事件
     */
    SUBSCRIBE,

    /**
     * 取消关注事件
     */
    UNSUBSCRIBE,

    /**
     * 模版消息发送任务完成事件
     */
    TEMPLATESENDJOBFINISH

}
