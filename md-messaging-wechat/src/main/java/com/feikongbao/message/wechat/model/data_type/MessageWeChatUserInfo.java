package com.feikongbao.message.wechat.model.data_type;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;


/**
 * weChat user info.
 *
 * @author zili.wang
 * @date 2019/5/6 19:58
 */
@Table(name = "messaging_wechat_user_info")
public class MessageWeChatUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userInfoId;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    @Column
    @JsonProperty("subscribe")
    private String userSubscribe;

    /**
     * 用户的标识，对当前公众号唯一
     */
    @NotEmpty
    @JsonProperty("openid")
    @Column
    private String userOpenId;

    /**
     * 用户绑定的手机号码
     */
    private String userPhoneNum;

    /**
     * 用户的昵称
     */
    @JsonProperty("nickname")
    private String userNickname;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    @JsonProperty("sex")
    private String userSex;

    /**
     * 用户所在城市
     */
    @JsonProperty("city")
    private String userCity;

    /**
     * 用户所在国家
     */
    @JsonProperty("country")
    private String userCountry;

    /**
     * 用户所在省份
     */
    @JsonProperty("province")
    private String userProvince;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    @JsonProperty("language")
    private String userLanguage;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
     * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。.
     */
    @JsonProperty("headimgurl")
    private String userHeadImgUrl;

    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @JsonProperty("subscribe_time")
    private String userSubscribeTime;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    @JsonProperty("unionid")
    private String userUnionId;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    @JsonProperty("remark")
    private String userRemark;

    /**
     * 用户所在的分组ID
     */
    @JsonProperty("groupid")
    private String userGroupId;

    /**
     * 用户被打上的标签ID列表.
     */
    @JsonProperty("tagid_list")
    @Transient
    private ArrayList userTagIdList;

    @Column
    private String userTagIdString;

    /**
     * 返回用户关注的渠道来源
     */
    @JsonProperty("subscribe_scene")
    private String userSubscribeScene;

    /**
     * 二维码扫码场景（开发者自定义）
     */
    @JsonProperty("qr_scene")
    private String userQrScene;

    /**
     * 二维码扫码场景描述（开发者自定义）
     */
    @JsonProperty("qr_scene_str")
    private String userQrSceneStr;

    @JsonProperty("errcode")
    @Transient
    private String errCode;

    @JsonProperty("errmsg")
    @Transient
    private String errMsg;

    @Column
    private Instant createTime;

    @Column
    private Instant lastUpdateTime;

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

    public String getUserTagIdString() {
        return userTagIdString;
    }

    public void setUserTagIdString(String userTagIdString) {
        this.userTagIdString = userTagIdString;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserSubscribe() {
        return userSubscribe;
    }

    public void setUserSubscribe(String userSubscribe) {
        this.userSubscribe = userSubscribe;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public String getUserHeadImgUrl() {
        return userHeadImgUrl;
    }

    public void setUserHeadImgUrl(String userHeadImgUrl) {
        this.userHeadImgUrl = userHeadImgUrl;
    }

    public String getUserSubscribeTime() {
        return userSubscribeTime;
    }

    public void setUserSubscribeTime(String userSubscribeTime) {
        this.userSubscribeTime = userSubscribeTime;
    }

    public String getUserUnionId() {
        return userUnionId;
    }

    public void setUserUnionId(String userUnionId) {
        this.userUnionId = userUnionId;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }

    public ArrayList getUserTagIdList() {
        return userTagIdList;
    }

    public void setUserTagIdList(ArrayList userTagIdList) {
        this.userTagIdList = userTagIdList;
    }

    public String getUserSubscribeScene() {
        return userSubscribeScene;
    }

    public void setUserSubscribeScene(String userSubscribeScene) {
        this.userSubscribeScene = userSubscribeScene;
    }

    public String getUserQrScene() {
        return userQrScene;
    }

    public void setUserQrScene(String userQrScene) {
        this.userQrScene = userQrScene;
    }

    public String getUserQrSceneStr() {
        return userQrSceneStr;
    }

    public void setUserQrSceneStr(String userQrSceneStr) {
        this.userQrSceneStr = userQrSceneStr;
    }
}
