package com.feikongbao.message.wechat.client.model.entiy.message_wechat;

import java.time.Instant;
import java.util.Date;

/**
 * The type Message we chat user message.
 *
 * @author zili.wang
 * @date 2019/05/09 11:27:58
 */
public class MessageWeChatUserMessage {
    /**
     * The User message id.
     */
    private Long userMessageId;

    /**
     * The User phone num.
     */
    private Long userPhoneNum;

    /**
     * The User open id.
     */
    private String userOpenId;

    /**
     * The User message msg id.
     */
    private String userMessageMsgId;

    /**
     * The User message status.
     */
    private String userMessageStatus;

    /**
     * The Create time.
     */
    private Instant createTime;

    /**
     * The Last update time.
     */
    private Instant lastUpdateTime;

    /**
     * The User message content.
     */
    private String userMessageContent;

    private String errMessage;

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    /**
     * Gets user message id.
     *
     * @return the user message id
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public Long getUserMessageId() {
        return userMessageId;
    }

    /**
     * Sets user message id.
     *
     * @param userMessageId the user message id
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public void setUserMessageId(Long userMessageId) {
        this.userMessageId = userMessageId;
    }

    /**
     * Gets user phone num.
     *
     * @return the user phone num
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public Long getUserPhoneNum() {
        return userPhoneNum;
    }

    /**
     * Sets user phone num.
     *
     * @param userPhoneNum the user phone num
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public void setUserPhoneNum(Long userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    /**
     * Gets user open id.
     *
     * @return the user open id
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public String getUserOpenId() {
        return userOpenId;
    }

    /**
     * Sets user open id.
     *
     * @param userOpenId the user open id
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    /**
     * Gets user message msg id.
     *
     * @return the user message msg id
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public String getUserMessageMsgId() {
        return userMessageMsgId;
    }

    /**
     * Sets user message msg id.
     *
     * @param userMessageMsgId the user message msg id
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public void setUserMessageMsgId(String userMessageMsgId) {
        this.userMessageMsgId = userMessageMsgId;
    }

    /**
     * Gets user message status.
     *
     * @return the user message status
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public String getUserMessageStatus() {
        return userMessageStatus;
    }

    /**
     * Sets user message status.
     *
     * @param userMessageStatus the user message status
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public void setUserMessageStatus(String userMessageStatus) {
        this.userMessageStatus = userMessageStatus;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Instant lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * Gets user message content.
     *
     * @return the user message content
     * @author zili.wang
     * @date 2019 /05/09 11:27:58
     */
    public String getUserMessageContent() {
        return userMessageContent;
    }

    /**
     * Sets user message content.
     *
     * @param userMessageContent the user message content
     * @author zili.wang
     * @date 2019 /05/09 11:27:59
     */
    public void setUserMessageContent(String userMessageContent) {
        this.userMessageContent = userMessageContent;
    }
}