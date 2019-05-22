package com.feikongbao.message.wechat.client.util;

/**
 * The enum Message we chat client rabbit enum.
 *
 * @author zili.wang
 * @date 2019 /05/20 20:02:56
 */
public enum MessageWeChatClientRabbitEnum {

    /**
     * Exchange message we chat client rabbit enum.
     */
    EXCHANGE(""),

    /**
     * Routingkey message we chat client rabbit enum.
     */
    ROUTING_KEY("");

    private final String value;

    public String getValue() {
        return value;
    }

    MessageWeChatClientRabbitEnum(String value) {
        this.value = value;
    }

}


