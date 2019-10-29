package com.mixin.messaging.email.client.exception;

/**
 * @author Peng Fan
 * @date 10/28/2019
 */
public class EmailSenderClientException extends Exception {
    private String messageBundleKey;

    private String[] messageParams;

    public EmailSenderClientException() {
        super();
    }

    public EmailSenderClientException(String messageBundleKey, String...messageParams) {
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
