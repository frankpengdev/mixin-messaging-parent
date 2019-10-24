package com.mixin.messaging.wechat.util;

/**
 * The enum We chat http status code enum.
 *
 * @author zili.wang
 */
public enum WeChatHttpStatusCodeEnum {

    /**
     * The Error.
     */
    ERROR(-1,"System is busy. Developers should try again later"),
    /**
     * The Ok.
     */
    OK(0,"Successful request"),
    /**
     * The Invalid access token or appsecret error.
     */
    INVALID_ACCESS_TOKEN_OR_APPSECRET_ERROR (40001,"There was an AppSecret error when getting access_token or the access_token was invalid"),
    /**
     * 无效的凭证类型
     */
    INVALID_CREDENTIAL_TYPE (40002,"Invalid credential type"),
    /**
     * Invalid openid we chat http status code enum.
     */
    INVALID_OPENID (40003,"InvalidOpenID"),

    /**
     * The Invalid appid.
     */
    INVALID_APPID(40013,"Invalid AppID"),
    /**
     * The Invalid access token.
     */
    INVALID_ACCESS_TOKEN(40014,"Invalid access_token"),

    /**
     * The Invalid menu buttonurl length.
     */
    INVALID_MENU_BUTTONURL_LENGTH(40020,"Invalid menu buttonURL length"),

    /**
     * The Invalid oauth code.
     */
    INVALID_OAUTH_CODE(40029,"Invalid oauth_code"),
    /**
     * The Invalid refresh token.
     */
    INVALID_REFRESH_TOKEN(40030,"Invalid refresh_token"),
    /**
     * The Invalid openid list.
     */
    INVALID_OPENID_LIST(40031,"Invalid OpenID list"),
    /**
     * The Invalid openid list length.
     */
    INVALID_OPENID_LIST_LENGTH(40032,"Invalid OpenID list length"),
    /**
     * The Invalid character in request.
     */
    INVALID_CHARACTER_IN_REQUEST(40033,"Invalid character in request"),
    /**
     * The Invalid parameter.
     */
    INVALID_PARAMETER(40035,"Invalid parameter"),
    /**
     * The Invalid request format.
     */
    INVALID_REQUEST_FORMAT(40038,"Invalid request format"),
    /**
     * The Invalid url length.
     */
    INVALID_URL_LENGTH(40039,"Invalid URL length"),
    /**
     * The Invalid url.
     */
    INVALID_URL(40048,"Invalid URL"),
    /**
     * The Invalid app secret.
     */
    INVALID_APP_SECRET(40125,"Invalid AppSecret"),
    /**
     * The Invalid wechat id.
     */
    INVALID_WECHAT_ID(40132,"Invalid WeChat ID"),

    /**
     * The Missing access token parameter.
     */
    MISSING_ACCESS_TOKEN_PARAMETER(41001,"Missing access_token parameter"),
    /**
     * The Missing appid parameter.
     */
    MISSING_APPID_PARAMETER(41002,"Missing appid parameter"),

    /**
     * The User does not exist.
     */
    USER_DOES_NOT_EXIST(46004,"User does not exist"),
    /**
     * The User does not have permission to use this api.
     */
    USER_DOES_NOT_HAVE_PERMISSION_TO_USE_THIS_API(50001,"The user does not have permission to use this AP"),
    /**
     * 用户没关注公众号
     */
    USER_IS_UNSUBSCRIBED(50005,"user is unsubscribed"),
    /**
     * The System error.
     */
    SYSTEM_ERROR(61450,"System error"),
    /**
     * The Wrongful url.
     */
    WRONGFUL_URL_(65317,"Wrongful URL"),

    /**
     * The Remote service is not available.
     */
    REMOTE_SERVICE_IS_NOT_AVAILABLE(9001002,"Remote service is not available"),;


    private final Integer code;

    private final String description;

    private WeChatHttpStatusCodeEnum(Integer code,String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
