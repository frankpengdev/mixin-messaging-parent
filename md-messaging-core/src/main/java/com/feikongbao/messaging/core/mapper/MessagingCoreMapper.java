package com.feikongbao.messaging.core.mapper;

import com.feikongbao.messaging.core.domain.MessagingCore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author jinjun_luo
 * @Date 2019/4/11 11:10
 **/
public interface MessagingCoreMapper {

    /**
     * 通过主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，每个字段都要有值
     * @param record
     * @return
     */
    int insert(MessagingCore record);

    /**
     * 插入，不是每个字段都要求有值
     * @param record
     * @return
     */
    int insertSelective(MessagingCore record);

    /**
     * 通过主键查询
     * @param id
     * @return
     */
    MessagingCore selectByPrimaryKey(Long id);

    /**
     * 通过主键更新，部分字段
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MessagingCore record);

    /**
     * 通过主键更新，全部字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(MessagingCore record);
}