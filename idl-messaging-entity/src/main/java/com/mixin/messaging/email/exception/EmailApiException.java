package com.mixin.messaging.email.exception;

/**
 * @Description 发送邮件自定义异常
 * @Author jinjun_luo
 * @Date 2019/4/16
 **/
public class EmailApiException extends Exception{
    private String messageBundleKey;

    private String[] messageParams;

    public EmailApiException() {
        super();
    }

    public EmailApiException(String messageBundleKey, String...messageParams) {
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
