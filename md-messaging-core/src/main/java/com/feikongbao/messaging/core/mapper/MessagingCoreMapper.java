package com.feikongbao.messaging.core.mapper;

import com.feikongbao.messaging.core.domain.MessagingCore;
import com.feikongbao.messaging.core.domain.MessagingCoreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author jinjun_luo
 * @Date 2019/4/11 11:10
 **/
public interface MessagingCoreMapper {
    long countByExample(MessagingCoreExample example);

    int deleteByExample(MessagingCoreExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MessagingCore record);

    int insertSelective(MessagingCore record);

    List<MessagingCore> selectByExample(MessagingCoreExample example);

    MessagingCore selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MessagingCore record, @Param("example") MessagingCoreExample example);

    int updateByExample(@Param("record") MessagingCore record, @Param("example") MessagingCoreExample example);

    int updateByPrimaryKeySelective(MessagingCore record);

    int updateByPrimaryKey(MessagingCore record);
}