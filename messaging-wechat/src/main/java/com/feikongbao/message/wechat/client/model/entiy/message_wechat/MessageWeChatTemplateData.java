package com.feikongbao.message.wechat.client.model.entiy.message_wechat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

/**
 * MessageWeChatMiniProgramData
 * @author Wang Zi Li
 * @date 2019/4/18 16:49
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageWeChatTemplateData {

    /**
     * 接收者openid
     */
    @NotEmpty
    private String touser;

    /**
     * 模板ID
     */
    @NotEmpty
    @JsonProperty("template_id")
    private String templateId;

    /**
     * 模板跳转链接
     */
    private String url;

    /**
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    private MessageWeChatMiniProgramData miniprogram;

    /**
     * 模板数据
     */
    private Map<String, MessageWeChatTemplateDataValue> data;

    @JsonProperty("phone_num")
    private Long phoneNum;

    public Long getPhoneNum() {
        return phoneNum;
    }

    @JsonIgnore
    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MessageWeChatMiniProgramData getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(MessageWeChatMiniProgramData miniprogram) {
        this.miniprogram = miniprogram;
    }

    public Map<String, MessageWeChatTemplateDataValue> getData() {
        return data;
    }

    public void setData(Map<String, MessageWeChatTemplateDataValue> data) {
        this.data = data;
    }
}
