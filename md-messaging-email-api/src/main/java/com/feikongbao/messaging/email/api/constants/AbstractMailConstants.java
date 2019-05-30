package com.feikongbao.messaging.email.api.constants;

/**
 * @Description 发送邮件使用常量
 * @Author jinjun_luo
 * @Date 2019/4/12 13:46
 **/
public abstract class AbstractMailConstants {

    /** 成功*/
    public final static Integer SUCCESSFUL_CODE = 200;

    /** 失败*/
    public final static Integer FALSE_CODE = 400;

    /*** 发件人*/
    public final static String FROM_EMPTY = "发件人为空";

    /*** 邮件发送给谁 可以发送多个人*/
    public final static String TO_EMPTY = "收件人为空";

    /*** 邮件标题*/
    public final static String SUBJECT_EMPTY = "邮件标题为空";

    /*** 邮件正文*/
    public final static String CONTENT_EMPTY = "邮件正文为空";

    /*** 验证邮箱正则*/
    public final static String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

}
