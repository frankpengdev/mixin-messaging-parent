package com.mixin.messaging.core.service;

import com.mixin.messaging.core.domain.MessagingCore;
import com.mixin.messaging.core.mapper.MessagingCoreMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.mixin.config.company.CompanyMySqlDataSourceConfig.COMPANY_MYSQL_DS_TX_MGR_BEAN_NAME;

/**
 * @Description 发送消息失败保存到数据库表
 * @Author jinjun_luo
 * @Date 2019/4/25 14:55
 **/
@Service
@Transactional(rollbackFor = {Exception.class}, transactionManager = COMPANY_MYSQL_DS_TX_MGR_BEAN_NAME)
public class MessagingCoreService {

    @Autowired
    private MessagingCoreMapper messagingCoreMapper;

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
    public Integer saveReturnedMessageStorage(String userId, String uuid, String exchange, String routingKey, int replyCode, String replyText, String messageJson){
        MessagingCore rms = new MessagingCore();
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
        return messagingCoreMapper.insertSelective(rms);
    }

}
