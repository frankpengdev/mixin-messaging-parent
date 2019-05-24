package com.feikongbao.messaging.email.api.enums;

import java.util.Objects;

/**
 * @Description 消息类型判断
 * @Author jinjun_luo
 * @Date 2019/4/11 9:33
 **/
public enum MailEnum {

    /*** 发件人*/
    FROM_EMPTY("发件人为空"),

    /*** 邮件发送给谁 可以发送多个人*/
    TO_EMPTY("收件人为空"),

    /*** 邮件标题*/
    SUBJECT_EMPTY("邮件标题为空"),

    /*** 邮件正文*/
    CONTENT_EMPTY("邮件正文为空");

    public final String TYPE_NAME;

    MailEnum(String typeName) {
        this.TYPE_NAME = typeName;
    }
    private static final EnumFindHelper<MailEnum, String> TYPE_NAME_FIND_HELPER = EnumFindHelper
            .of(MailEnum.class, e -> e.TYPE_NAME);
    /**
     * 根据名称查找枚举
     *
     * @param typeName 名称
     * @return 名称对应的枚举
     * @throws IllegalArgumentException 未找到枚举
     */
    public static MailEnum getByTypeName(String typeName) {
        MailEnum r = TYPE_NAME_FIND_HELPER.find(typeName, null);
        if (Objects.isNull(r)) {
            throw new IllegalArgumentException(
                    MailEnum.class.getCanonicalName() + "中未找到typeName为" + typeName + "的枚举. ");
        }
        return r;
    }
}
