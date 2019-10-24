package com.mixin.messaging.email.api.entity;

/**
 * @Description 指定发送邮件的服务器
 * @Author jinjun_luo
 * @Date 2019/4/29 11:09
 **/
public class EmailServiceEntity {

    /** 用户名，如果需要指定邮箱服务必须指定用户名*/
    private String username;

    /** 密码，如果需要指定邮箱服务器必须指定密码*/
    private String password;

    /** 地址，如果需要指定邮箱服务器必须指定邮箱地址*/
    private String host;

    /** 端口号，不传使用邮件本身的默认端口号*/
    private Integer port;

    /** 使用协议默认 不指定默认为 smtp*/
    private String protocol;

    /** 是否需要认证,不指定默认为 true*/
    private Boolean auth;

    /** 编码 默认, 不指定默认为 UTF-8*/
    private String encoding;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
