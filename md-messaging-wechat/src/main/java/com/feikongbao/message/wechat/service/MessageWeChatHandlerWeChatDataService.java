package com.feikongbao.message.wechat.service;

import com.feikongbao.message.wechat.client.model.entiy.message_wechat.MessageWeChatUserMessage;
import com.feikongbao.message.wechat.client.service.MessageWeChatSendTemplateMsgService;
import com.feikongbao.message.wechat.exception.MessageWeChatException;
import com.feikongbao.message.wechat.model.data_type.MessageWeChatUserInfo;
import com.feikongbao.message.wechat.model.mapper.MessageWeChatUserInfoMapper;
import com.feikongbao.message.wechat.model.mapper.MessageWeChatUserMessageMapper;
import com.feikongbao.message.wechat.util.MessageWeChatHelpUtil;
import com.feikongbao.message.wechat.util.MessageWeChatRequestUrlEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zili.wang
 * @date 2019/4/30 10:38
 */
@Service
public class MessageWeChatHandlerWeChatDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageWeChatHandlerWeChatDataService.class);

    /**
     * accessToken在redis中的key
     */
    private static final String ACCESS_TOKEN_CACHE_KEY = "cache:redis:wechat:accessToken";

    private static final String ERR_CODE = "errcode";

    private static final String TOKEN_URL = MessageWeChatRequestUrlEnum.WECHAT_TOKEN_REQUEST_URL.getDescription();
    private static final String USER_INFO_URL = MessageWeChatRequestUrlEnum.WECHAT_USER_INFO_REQUEST_URL.getDescription();
    private static final String WEB_ACCESS_TOKEN = MessageWeChatRequestUrlEnum.WECHAT_WEB_ACCESS_TOKEN_REQUEST_URL.getDescription();
    private static final String WECHAT_APP_ID = MessageWeChatRequestUrlEnum.WECHAT_APP_ID.getDescription();
    private static final String WECHAT_APP_SECRET = MessageWeChatRequestUrlEnum.WECHAT_APP_SECRET.getDescription();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MessageWeChatUserInfoMapper weChatInfoMapper;

    @Autowired
    private MessageWeChatUserMessageMapper userMessageMapper;

    @Autowired
    private MessageWeChatSendTemplateMsgService sendTemplateMsgService;


    /**
     * 将微信返回的模板消息处理结果更新到对应的消息中
     *
     * @param status the status
     * @param openId the open id
     * @param msgId  the msg id
     * @author zili.wang
     * @date 2019/05/21 20:57:45
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, transactionManager = "messageWeChatTransactionManager")
    public void updateWeChatMessageStatus(String status, String openId, String msgId) {
        MessageWeChatUserMessage weChatUserMessage = new MessageWeChatUserMessage();
        weChatUserMessage.setUserOpenId(openId);
        weChatUserMessage.setUserMessageMsgId(msgId);
        weChatUserMessage.setLastUpdateTime(Instant.now());
        String KEY_SUCCESS = "success";
        if (KEY_SUCCESS.equals(status)) {
            weChatUserMessage.setUserMessageStatus("COMPLETE");
        }else {
            weChatUserMessage.setUserMessageStatus("TODO");
        }
        weChatUserMessage.setErrMessage(status);
        userMessageMapper.updateUserMessageStatus(weChatUserMessage);
    }

    /**
     * Delete user by open id.
     *
     * @param openId the open id
     * @author zili.wang
     * @date 2019 /05/08 11:23:07
     */
    public void deleteUserByOpenId(String openId) {
        weChatInfoMapper.deleteByOpenId(openId);
    }

    /**
     * Count select by open id integer.
     *
     * @param openId the open id
     * @return the integer
     * @author zili.wang
     * @date 2019/05/08 11:22:19
     */
    public Integer countSelectByOpenId(String openId) {
        return weChatInfoMapper.countSelectByOpenId(openId);
    }

    /**
     * 用openId来获取用户的基本信息
     *
     * @param openId   the open id
     * @param phoneNum the phone num
     * @throws MessageWeChatException the message we chat exception
     * @author zili.wang1
     * @date 2019 /05/06 20:09:08
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, transactionManager = "messageWeChatTransactionManager")
    public void getAndInsertWeChatUserInfo(String openId, String phoneNum) throws MessageWeChatException {

        String accessToken = getWeChatAccessTokenFromCache();

        ResponseEntity<MessageWeChatUserInfo> responseEntity =
                restTemplate.getForEntity(USER_INFO_URL, MessageWeChatUserInfo.class, accessToken, openId);

        MessageWeChatUserInfo weChatUserInfo = responseEntity.getBody();

        if (StringUtils.isNoneBlank(weChatUserInfo.getErrCode())) {
            LOGGER.error("errCode: " + weChatUserInfo.getErrCode() + " errMsg: " + weChatUserInfo.getErrMsg());
            throw new MessageWeChatException("messaging-wechat.failure.get.user.info", weChatUserInfo.getErrCode(), weChatUserInfo.getErrMsg());
        }

        weChatUserInfo.setUserPhoneNum(phoneNum);
        if (weChatUserInfo.getUserTagIdList() != null) {
            weChatUserInfo.setUserTagIdString(weChatUserInfo.getUserTagIdList().toString());
        }

        weChatUserInfo.setLastUpdateTime(Instant.now());

        weChatInfoMapper.insertWeChatUserInfo(weChatUserInfo);

    }

    /**
     * Gets wechat access token from cache.
     *
     * @return the we chat access token from cache
     * @throws MessageWeChatException the message we chat exception
     * @author zili.wang
     * @date 2019/05/07 11:05:57
     */
    public String getWeChatAccessTokenFromCache() throws MessageWeChatException {
        String accessToken = null;
        if (redisTemplate.hasKey(ACCESS_TOKEN_CACHE_KEY)) {
            accessToken = redisTemplate.opsForValue().get(ACCESS_TOKEN_CACHE_KEY).toString();
        }
        if (StringUtils.isBlank(accessToken)) {
            accessToken = this.getWeChatAccessToken();
        }
        return accessToken;
    }

    /**
     * 获取token
     *
     * @return we chat access token
     * @throws MessageWeChatException the message wechat exception
     * @author zili.wang
     * @date 2019/05/07 11:03:17
     */
    public String getWeChatAccessToken() throws MessageWeChatException {

        ConcurrentHashMap<String, String> paramsHashMap = new ConcurrentHashMap<>(16);
        paramsHashMap.put("appId", WECHAT_APP_ID);
        paramsHashMap.put("appSecret", WECHAT_APP_SECRET);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(TOKEN_URL, String.class, paramsHashMap);
        String responseMessage = responseEntity.getBody().toString();
        responseEntity.getStatusCode().is1xxInformational();

        Map<String, String> responseHashMap = MessageWeChatHelpUtil.json2Map(responseMessage);

        if (null != responseHashMap.get(ERR_CODE)) {
            String errMsg = responseHashMap.get("errmsg");
            String errCodeValue = responseHashMap.get(ERR_CODE);
            LOGGER.error("weChat get accessToken error: {}, {}", errCodeValue, errMsg);
            throw new MessageWeChatException("messaging-wechat.get.accesstoken.error", errMsg);
        }

        String accessToken = responseHashMap.get("access_token");
        String expiresIn = responseHashMap.get("expires_in");

        //缓存token
        redisTemplate.opsForValue().set(ACCESS_TOKEN_CACHE_KEY, accessToken, Long.parseLong(expiresIn), TimeUnit.SECONDS);

        return accessToken;

    }

    /**
     * Gets we chat user open id.
     *
     * @param code the code
     * @return the we chat user open id
     * @throws MessageWeChatException the message wechat exception
     * @author zili.wang
     * @date 2019/05/06 20:22:34
     */
    public String getWeChatUserOpenId(String code) throws MessageWeChatException {
        ConcurrentHashMap<String, String> paramsHashMap = new ConcurrentHashMap<>(16);
        paramsHashMap.put("appId", WECHAT_APP_ID);
        paramsHashMap.put("secret", WECHAT_APP_SECRET);
        paramsHashMap.put("code", code);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(WEB_ACCESS_TOKEN, String.class, paramsHashMap);
        String responseMessage = responseEntity.getBody().toString();

        Map<String, String> responseOpenHashMap = MessageWeChatHelpUtil.json2Map(responseMessage);

        if (null != responseOpenHashMap.get(ERR_CODE)) {
            String errMsg = responseOpenHashMap.get("responseHashMap");
            throw new MessageWeChatException("BAD_REQUEST123{0}", errMsg);
        }

        //网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
        String accessToken = responseOpenHashMap.get("accessToken");
        //access_token接口调用凭证超时时间，单位（秒）
        String expiresIn = responseOpenHashMap.get("expiresIn");
        //用户刷新access_token
        String refreshToken = responseOpenHashMap.get("refreshToken");
        //用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
        String openId = responseOpenHashMap.get("openId");
        //用户授权的作用域，使用逗号（,）分隔
        String scope = responseOpenHashMap.get("scope");

        return openId;

    }
}
