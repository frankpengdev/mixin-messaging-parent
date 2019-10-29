package com.mixin.messaging.wechat.client.model.entiy.message_wechat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Wang Zi Li
 * @date 2019/4/18 17:32
 */
public class MessageWeChatResponseEntity {

    @JsonProperty("errcode")
    private String errCode;

    @JsonProperty("errmsg")
    private String errMsg;

    @JsonProperty("msgid")
    private String msgId;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
