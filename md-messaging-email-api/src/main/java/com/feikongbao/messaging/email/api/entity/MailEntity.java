package com.feikongbao.messaging.email.api.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 封装邮件实体
 * @Author jinjun_luo
 * @Date 2019/4/11 16:02
 **/
public class MailEntity /*implements Serializable*/ {

//    private static final long serialVersionUID = -7167590352111605597L;

    /*** 发件人*/
    private String from;

    /*** 邮件发送给谁 可以发送多个人*/
    private List<String> to = new ArrayList<>();

    /*** 邮件抄送给谁 可以抄送多个人*/
    private List<String> cc = new ArrayList<>();

    /*** 邮件密送给谁 可以密送多个人*/
    private List<String> bcc = new ArrayList<>();

    /*** 邮件标题*/
    private String subject;

    /*** 邮件正文*/
    private String content;

    /*** 附件*/
    private List<String> filePaths = new ArrayList<>();

    /** 用于指定邮件服务器传参*/
    private EmailServiceEntity emailServiceEntity;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public EmailServiceEntity getEmailServiceEntity() {
        return emailServiceEntity;
    }

    public void setEmailServiceEntity(EmailServiceEntity emailServiceEntity) {
        this.emailServiceEntity = emailServiceEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
