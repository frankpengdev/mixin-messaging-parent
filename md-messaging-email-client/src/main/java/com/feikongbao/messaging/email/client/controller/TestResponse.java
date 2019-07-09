package com.feikongbao.messaging.email.client.controller;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/6/13 16:50
 **/
public class TestResponse {

    private int status;
    private String message;

    public TestResponse() {

    }

    public TestResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
