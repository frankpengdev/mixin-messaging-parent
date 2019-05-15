package com.feikongbao.message.wechat.client.model.entiy.message_wechat;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

/**
 * MessageWechatMiniProgramData的miniprogram数据
 * @author Wang Zi Li
 * @date 2019/4/18 17:07
 */
public class MessageWeChatMiniProgramData {

    /**
     * 所需跳转到的小程序appid,该小程序appid必须与发模板消息的公众号是绑定关联关系
     */
    @JsonProperty("appid")
    private String appId;

    /**
     * 所需跳转到小程序的具体页面路径,支持带参数,（示例index?foo=bar），要求该小程序已发布
     */
    @JsonProperty("pagepath")
    private String pagePath;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }
}
