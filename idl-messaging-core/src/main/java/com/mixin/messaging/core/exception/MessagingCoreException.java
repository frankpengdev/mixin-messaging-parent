package com.mixin.messaging.core.exception;

/**
 * @Author jinjun_luo
 * @Date 2019/4/16
 **/
public class MessagingCoreException extends Exception{

    private String messageBundleKey;

    private String[] messageParams;

    public MessagingCoreException() {
        super();
    }

    public MessagingCoreException(String messageBundleKey, String...messageParams) {
        super(messageBundleKey);
        this.messageParams = messageParams;
        this.messageBundleKey = messageBundleKey;
    }

    public String getMessageBundleKey() {
        return messageBundleKey;
    }

    public void setMessageBundleKey(String messageBundleKey) {
        this.messageBundleKey = messageBundleKey;
    }

    public String[] getMessageParams() {
        return messageParams;
    }

    public void setMessageParams(String[] messageParams) {
        this.messageParams = messageParams;
    }
}
