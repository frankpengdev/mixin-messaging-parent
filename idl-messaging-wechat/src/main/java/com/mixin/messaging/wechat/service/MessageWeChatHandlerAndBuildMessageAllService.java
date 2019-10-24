package com.mixin.messaging.wechat.service;

import com.mixin.messaging.wechat.exception.MessageWeChatException;
import com.mixin.messaging.wechat.model.data_type.MessageWeChatResponseMessage;
import com.mixin.messaging.wechat.util.MessageWeChatHelpUtil;
import com.mixin.messaging.wechat.util.WeChatMessageTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 处理微信发送过来的消息并构造返回消息
 *
 * @author zili.wang
 * @date 2019/5/8 14:31
 */
@Service
public class MessageWeChatHandlerAndBuildMessageAllService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageWeChatHandlerAndBuildMessageAllService.class);

    @Autowired
    private MessageWeChatHandlerWeChatDataService weChatDataService;


    /**
     * Handler text message string.
     *
     * @param responseMessage the response message
     * @param openId          the open id
     * @param requestContent  the request content
     * @return the string
     * @author zili.wang
     * @date 2019/05/08 16:14:32
     */
    public String handlerTextMessage(MessageWeChatResponseMessage responseMessage, String openId, String requestContent) {

        responseMessage.setCreateTime(System.currentTimeMillis());
        responseMessage.setMsgType(WeChatMessageTypeEnum.TEXT.name());

        Boolean flag = MessageWeChatHelpUtil.validatePhoneNumber(requestContent);
        if (flag) {
            String lastFourNum = requestContent.substring(7, 11);
            //查询用户是否已经绑定了手机号码
            int count = weChatDataService.countSelectByOpenId(openId);
            if (count > 0) {
                responseMessage.setContent("尾号" + lastFourNum + "手机已绑定\n如需更换请在个人中心操作");
            } else {
                // 手机验证成功 绑定用户信息
                try {
                    weChatDataService.getAndInsertWeChatUserInfo(openId, requestContent);
                    responseMessage.setContent("绑定尾号" + lastFourNum + "手机成功");
                } catch (MessageWeChatException e) {
                    responseMessage.setContent("服务器错误请稍后重试...");
                }
            }
        } else {
            responseMessage.setContent("手机号码格式有误，请重新输入:  " + requestContent);
        }

        return MessageWeChatHelpUtil.object2Xml(responseMessage);
    }

    /**
     * Handler event message.
     *
     * @param responseMessage the response message
     * @param openId          the open id
     * @param requestMap      the request map
     * @return the string
     * @author zili.wang
     * @date 2019 /05/08 16:14:39
     */
    public String handlerEventMessage(MessageWeChatResponseMessage responseMessage, String openId, Map<String, String> requestMap) {
        String event = requestMap.get("Event");
        String status = requestMap.get("Status");
        String msgId = requestMap.get("MsgID");
        String eventKey = requestMap.get("EventKey");

        //取消关注 删除用户信息
        if (WeChatMessageTypeEnum.UNSUBSCRIBE.name().equals(event.toUpperCase())) {
            weChatDataService.deleteUserByOpenId(openId);
        }

        // 关注时 自动回复消息
        if (WeChatMessageTypeEnum.SUBSCRIBE.name().equals(event.toUpperCase())) {
            responseMessage.setCreateTime(System.currentTimeMillis());
            responseMessage.setMsgType(WeChatMessageTypeEnum.TEXT.name());
            responseMessage.setContent("您好，欢迎关注费控宝SAAS！\n\n请输入您的手机号码:\n\n根据提示完成绑定");
        }

        //click事件
        String CLICK = "CLICK";
        if (CLICK.equals(event)) {
            if (eventKey.equals("V1001_GOOD")) {
                responseMessage.setCreateTime(System.currentTimeMillis());
                responseMessage.setMsgType(WeChatMessageTypeEnum.TEXT.name());
                responseMessage.setContent("谢谢点赞!");

            }
            if ("V1001_FEIKONGBAO_Mall".equals(eventKey)) {
                responseMessage.setCreateTime(System.currentTimeMillis());
                responseMessage.setMsgType(WeChatMessageTypeEnum.TEXT.name());
                responseMessage.setContent("开发中...正在努力 敬请期待！");
            }
        }

        return MessageWeChatHelpUtil.object2Xml(responseMessage);
    }

}
