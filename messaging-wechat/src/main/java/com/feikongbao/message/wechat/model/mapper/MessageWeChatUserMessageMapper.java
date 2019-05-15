package com.feikongbao.message.wechat.model.mapper;

import com.feikongbao.message.wechat.client.model.entiy.message_wechat.MessageWeChatUserMessage;

import java.util.List;


/**
 * The interface We chat user message mapper.
 *
 * @author zili.wang
 * @date 2019/05/09 11:27:27
 */
public interface MessageWeChatUserMessageMapper {

    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     * @author zili.wang
     * @date 2019/05/09 11:27:27
     */
    int insert(MessageWeChatUserMessage record);


    /**
     * Select all list.
     *
     * @return the list
     * @author zili.wang
     * @date 2019/05/09 11:27:27
     */
    List<MessageWeChatUserMessage> selectAll();

    /**
     * Update user message status.
     *
     * @param weChatUserMessage the we chat user message
     * @return the integer
     * @author zili.wang
     * @date 2019/05/13 10:28:19
     */
    Integer updateUserMessageStatus(MessageWeChatUserMessage weChatUserMessage);
}