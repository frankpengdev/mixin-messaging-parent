package com.mixin.messaging.wechat.client.model.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * MessageWeChatClientMiniProgramData
 *
 * @author Wang Zi Li
 * @date 2019/4/18 16:49
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"phoneNum", "touser", "templateId", "url", "miniprogram", "data"})
public class MessageWeChatClientTemplateData {

    @JsonProperty("phone_num")
    @NotEmpty(message = "The phone_num of MessageWeChatClientTemplateData can not be null")
    @Pattern(regexp = "^((13[0-9])|(14[1,5,7,9])|(15[0-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[1,8,9]))\\d{8}$",
            message = "The phoneNum of MessageWeChatClientTemplateData number format is wrong")
    private String phoneNum;

    /**
     * 接收者openid
     */
    private String touser;

    /**
     * 模板ID
     */
    @NotEmpty(message = "The templateId of MessageWeChatClientTemplateData can not be null")
    @JsonProperty("template_id")
    private String templateId;

    /**
     * 模板跳转链接
     */
    private String url;

    /**
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    private MessageWeChatClientMiniProgramData miniprogram;

    /**
     * 模板数据
     */
    @NotNull(message = "The data of MessageWeChatClientTemplateData can not be null")
    private Map<String, MessageWeChatClientTemplateDataValue> data;


    public String getPhoneNum() {
        return phoneNum;
    }

    @JsonIgnore
    public void setPhoneNum(String phoneNum) {
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

    public MessageWeChatClientMiniProgramData getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(MessageWeChatClientMiniProgramData miniprogram) {
        this.miniprogram = miniprogram;
    }

    public Map<String, MessageWeChatClientTemplateDataValue> getData() {
        return data;
    }

    public void setData(Map<String, MessageWeChatClientTemplateDataValue> data) {
        this.data = data;
    }
}
