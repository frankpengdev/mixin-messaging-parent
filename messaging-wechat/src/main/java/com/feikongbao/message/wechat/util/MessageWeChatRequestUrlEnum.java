package com.feikongbao.message.wechat.util;

/**
 * 记录跟微信公众号交互的信息
 *
 * @author zili.wang
 * @date 2019 /05/14 17:19:15
 */
public enum MessageWeChatRequestUrlEnum {

    /**
     * Wechat app id.
     */
    WECHAT_APP_ID("wx582d94ceb959eff5"),

    WECHAT_APP_SECRET("3b95de6cd130b69800ab41807b53a18d"),


    /**
     * 模板消息的url.
     */
    WECHAT_TEMPLATE_URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={0}"),

    /**
     * Wechat token request url.
     */
    WECHAT_TOKEN_REQUEST_URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appId}&secret={appSecret}"),

    /**
     * Wechat user info request url.
     */
    WECHAT_USER_INFO_REQUEST_URL("https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}&lang=zh_CN"),

    WECHAT_MENU_REQUEST_URL("https://api.weixin.qq.com/cgi-bin/menu/create?access_token={}"),
    /**
     * 通过code换取网页授权access_token.
     */
    WECHAT_WEB_ACCESS_TOKEN_REQUEST_URL("https://api.weixin.qq.com/sns/oauth2/access_token?appid={appId}&secret={secret}&code={code}&grant_type=authorization_code");

    private final String description;


    MessageWeChatRequestUrlEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }}
