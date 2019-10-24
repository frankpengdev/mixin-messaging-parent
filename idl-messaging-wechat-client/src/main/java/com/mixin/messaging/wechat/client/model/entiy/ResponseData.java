package com.mixin.messaging.wechat.client.model.entiy;

/**
 * @author zili.wang
 * @date 2019/5/21 15:28
 */
public class ResponseData {

    private String errCode;

    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
