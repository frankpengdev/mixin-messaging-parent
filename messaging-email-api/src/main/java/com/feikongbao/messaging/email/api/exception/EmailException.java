package com.feikongbao.messaging.email.api.exception;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/16 17:07
 **/
public class EmailException extends Exception{

    private String messageBundleKey;

    private String[] messageParams;

    public EmailException() {
        super();
    }

    public EmailException(String messageBundleKey, String...messageParams) {
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
