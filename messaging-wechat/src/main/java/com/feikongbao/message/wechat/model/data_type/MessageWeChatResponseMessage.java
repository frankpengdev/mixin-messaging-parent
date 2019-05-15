package com.feikongbao.message.wechat.model.data_type;

/**
 * The type Message we chat response message.
 *
 * @author zili.wang
 * @date 2019/5/6 11:04
 */
public class MessageWeChatResponseMessage {

    /**
     * The To user name.
     */
    private String ToUserName;

    /**
     * The From user name.
     */
    private String FromUserName;

    /**
     * The Create time.
     */
    private Long CreateTime;

    /**
     * The Msg type.
     */
    private String MsgType;

    /**
     * The Content.
     */
    private String Content;

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }



    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}