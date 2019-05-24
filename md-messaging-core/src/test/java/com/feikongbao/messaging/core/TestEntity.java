package com.feikongbao.messaging.core;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName TestEntity
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/8 17:37
 * @Version 1.0
 **/
public class TestEntity  {
    private Integer id;
    private String userName;
    private Date createTime;

    public TestEntity() {
    }

    public TestEntity(Integer id, String userName, Date createTime) {
        this.id = id;
        this.userName = userName;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
