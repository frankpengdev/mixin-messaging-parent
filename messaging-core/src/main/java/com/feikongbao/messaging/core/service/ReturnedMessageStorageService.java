package com.feikongbao.messaging.core.service;

import com.feikongbao.messaging.core.config.MessagingCoreConfig;
import com.feikongbao.messaging.core.domain.ReturnedMessageStorage;
import com.feikongbao.messaging.core.mapper.ReturnedMessageStorageMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/25 14:55
 **/
@Service
@Transactional(rollbackFor = Exception.class, transactionManager = MessagingCoreConfig.MESSAGE_CORE_TRANSACTION_MANAGER)
public class ReturnedMessageStorageService {

    @Autowired
    private ReturnedMessageStorageMapper returnedMessageStorageMapper;

    /**
     * 保存消息从exchange 发到 queue 失败的数据
     * @param userId        用户id
     * @param uuid          消息随机编号
     * @param exchange      交换机
     * @param routingKey    路由键
     * @param replyCode     失败代码
     * @param replyText     失败原因
     * @param messageJson   发送失败的消息数据
     * @return
     */
    public Integer saveReturnedMessageStorage(Long userId, String uuid, String exchange, String routingKey, int replyCode, String replyText, String messageJson){
        ReturnedMessageStorage rms = new ReturnedMessageStorage();
        if (userId != null){
            rms.setUserId(userId);
        }
        if (StringUtils.isNotBlank(uuid)){
            rms.setMessageUuid(uuid);
        }
        if (StringUtils.isNotBlank(exchange)){
            rms.setExchange(exchange);
        }
        if (StringUtils.isNotBlank(routingKey)){
            rms.setRoutingKey(routingKey);
        }
        rms.setReplyCode(replyCode);
        if (StringUtils.isNotBlank(replyText)){
            rms.setReplyTest(replyText);
        }
        if (StringUtils.isNotBlank(messageJson)){
            rms.setMessageSend(messageJson);
        }
        rms.setStatus(0);
        rms.setCreateTime(new Date());
        rms.setUpdateTime(rms.getCreateTime());
        return returnedMessageStorageMapper.insertSelective(rms);
    }

}
