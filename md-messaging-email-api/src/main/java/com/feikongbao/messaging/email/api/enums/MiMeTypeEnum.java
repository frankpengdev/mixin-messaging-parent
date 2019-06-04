package com.feikongbao.messaging.email.api.enums;

import java.util.Objects;

/**
 * @Description 用于发邮件附件类型判断
 * @Author jinjun_luo
 * @Date 2019/6/3 19:48
 **/
public enum MiMeTypeEnum {

    //在线播放
    FLV(".flv", "flv/flv-flash"),

    //超文本标记语言文本
    HTML("html", "text/html"),

    //超文本标记语言文本
    HTM(".htm", "text/html"),

    //RTF文本
    RTF(".rtf", "application/rtf"),

    //GIF图形
    GIF(".gif", "image/gif"),

    //JPEG图形
    JPEG(".jpeg", "image/jpeg"),

    //JPG图形
    JPG(".jpg", "image/jpeg"),

    //au声音文件
    AU(".au", "audio/basic"),

    //MIDI音乐文件
    MID(".mid", "audio/midi"),

    //MIDI音乐文件
    MIDI(".midi", "audio/x-midi"),

    //RealAudio音乐文件
    RA(".ra", "audio/x-pn-realaudio"),

    //RealAudio音乐文件
    RAM(".ram", "audio/x-pn-realaudio"),

    //RealAudio音乐文件
    RM(".rm", "audio/x-pn-realaudio"),

    //MPEG文件
    MPG(".mpg", "video/mpeg"),

    //MPEG文件
    MPEG(".mpeg", "video/mpeg"),

    //MPEG文件
    MP3(".mp3", "video/mpeg"),

    //AVI文件
    AVI(".avi", "video/x-msvideo"),

    //GZIP文件
    GZ(".gz", "application/x-gzip"),

    //TAR文件
    TAR(".tar", "application/x-tar"),

    //下载文件类型
    EXE(".exe", "application/octet-stream"),

    //在线播放
    RMVB(".rmvb", "video/vnd.rn-realvideo"),

    //普通文本
    TXT(".txt", "text/plain"),

    //MRP文件（国内普遍的手机）
    MRP(".mrp", "application/octet-stream"),

    //IPA文件(IPHONE)
    IPA(".ipa", "application/iphone-package-archive"),

    //DED文件(IPHONE)
    DEB(".deb", "application/x-debian-package-archive"),

    //APK文件(安卓系统)
    APK(".apk", "application/vnd.android.package-archive"),

    //CAB文件(Windows Mobile)
    CAB(".cab", "application/vnd.cab-com-archive"),

    //XAP文件(Windows Phone 7)
    XAP(".xap", "application/x-silverlight-app"),

    //SIS文件(symbian平台)
    SIS(".sis", "application/vnd.symbian.install-archive"),

    //JAR文件(JAVA平台手机通用格式)
    JAR(".jar", "application/java-archive"),

    //JAD文件(JAVA平台手机通用格式)
    JAD(".jad", "text/vnd.sun.j2me.app-descriptor"),

    //SISX文件(symbian平台)
    SISX(".sisx", "application/vnd.symbian.epoc/x-sisx-app ");

    public String key;

    public String value;

    MiMeTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final EnumFindHelper<MiMeTypeEnum, String> KEY_FIND_HELPER = EnumFindHelper
            .of(MiMeTypeEnum.class, e -> e.key);

    private static final EnumFindHelper<MiMeTypeEnum, String> VALUE_FIND_HELPER = EnumFindHelper
            .of(MiMeTypeEnum.class, e -> e.value);

    
    /**
     * 根据编码查找枚举
     *
     * @param key 编码
     * @return 编码对应的枚举
     * @throws IllegalArgumentException 未找到枚举
     */
    public static MiMeTypeEnum getBykey(String key) {

        MiMeTypeEnum r = KEY_FIND_HELPER.find(key, null);
        if (Objects.isNull(r)) {
            throw new IllegalArgumentException(MiMeTypeEnum.class.getCanonicalName() + "中未找到key为" + key + "的枚举. ");
        }
        return r;
    }

    /**
     * 根据名称查找枚举
     *
     * @param typeName 名称
     * @return 名称对应的枚举
     * @throws IllegalArgumentException 未找到枚举
     */
    public static MiMeTypeEnum getByTypeName(String typeName) {
        MiMeTypeEnum r = VALUE_FIND_HELPER.find(typeName, null);
        if (Objects.isNull(r)) {
            throw new IllegalArgumentException(
                    MiMeTypeEnum.class.getCanonicalName() + "中未找到typeName为" + typeName + "的枚举. ");
        }
        return r;
    }
}
