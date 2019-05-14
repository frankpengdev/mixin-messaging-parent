package com.feikongbao.messaging.core.mapper;

import com.feikongbao.messaging.core.domain.ReturnedMessageStorage;
import com.feikongbao.messaging.core.domain.ReturnedMessageStorageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/11 11:10
 **/
public interface ReturnedMessageStorageMapper {
    long countByExample(ReturnedMessageStorageExample example);

    int deleteByExample(ReturnedMessageStorageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReturnedMessageStorage record);

    int insertSelective(ReturnedMessageStorage record);

    List<ReturnedMessageStorage> selectByExample(ReturnedMessageStorageExample example);

    ReturnedMessageStorage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReturnedMessageStorage record, @Param("example") ReturnedMessageStorageExample example);

    int updateByExample(@Param("record") ReturnedMessageStorage record, @Param("example") ReturnedMessageStorageExample example);

    int updateByPrimaryKeySelective(ReturnedMessageStorage record);

    int updateByPrimaryKey(ReturnedMessageStorage record);
}