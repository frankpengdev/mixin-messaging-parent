package com.feikongbao.message.wechat.exception;

/**
 * @author Wang Zi Li
 * @date 2019/4/17 15:23
 */
public class MessageWeChatException extends Exception {

    private String messageBundleKey;

    private String[] messageParams;

    public MessageWeChatException() {
        super();
    }

    public MessageWeChatException(String messageBundleKey, String... messageParams){
        super(messageBundleKey);
        this.messageBundleKey = messageBundleKey;
        this.messageParams = messageParams;
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
