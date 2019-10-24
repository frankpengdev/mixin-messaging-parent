package com.mixin.messaging.wechat.model.mapper;

import com.mixin.messaging.wechat.model.data_type.MessageWeChatUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * The interface Message we chat info mapper.
 *
 * @author zili.wang
 * @date 2019/5/5 11:28
 */
@Mapper
public interface MessageWeChatUserInfoMapper {


    /**
     * Insert we chat user info.
     *
     * @param messageWeChatUserInfo the message we chat user info
     * @author zili.wang
     * @date 2019/05/05 15:11:10
     */
    void insertWeChatUserInfo(MessageWeChatUserInfo messageWeChatUserInfo);

    /**
     * Select all message we chat user info.
     *
     * @return the message we chat user info
     * @author zili.wang
     * @date 2019/05/07 14:52:46
     */
    MessageWeChatUserInfo selectAll();


    /**
     * 查询用户是否已经绑定手机
     *
     * @param openId the open id
     * @return the integer
     * @author zili.wang
     * @date 2019/05/07 15:16:05
     */
    Integer countSelectByOpenId(@Param("openId") String openId);

    /**
     * Delete by open id.
     *
     * @param openId the open id
     * @author zili.wang
     * @date 2019/05/08 10:53:29
     */
    void deleteByOpenId(@Param("openId") String openId);

    /**
     * Select open id by phone num.
     *
     * @param phoneNum the phone num
     * @return string string
     * @author zili.wang
     * @date 2019/05/13 11:15:45
     */
    String selectOpenIdByPhoneNum(@Param("userPhoneNum") Long phoneNum);
}
