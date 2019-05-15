DROP TABLE IF EXISTS `fei_kong_bao_messaging_wechat`.`wechat_user_info`;
CREATE TABLE IF NOT EXISTS `fei_kong_bao_messaging_wechat`.`wechat_user_info`
(
  `user_info_id`         bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `user_open_id`         varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户的标识',
  `user_phone_num`       decimal(11, 0)                                          NOT NULL COMMENT '用户绑定的手机号',
  `user_subscribe`       varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '用户是否订阅该公众号标识 0没有关注',
  `user_nickname`        varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '用户的昵称',
  `user_sex`             int(1)                                                  NULL DEFAULT NULL COMMENT '用户的性别 值为1时是男性 值为2时是女性 值为0时是未知',
  `user_city`            varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户所在城市',
  `user_country`         varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户所在国家',
  `user_province`        varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户所在省份',
  `user_language`        varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '用户的语言 简体中文为zh_CN',
  `user_head_img_url`    varchar(240) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_subscribe_time`  int(11)                                                 NULL DEFAULT NULL COMMENT '用户关注时间',
  `user_union_id`        varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后才会出现该字段',
  `user_remark`          varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公众号运营者对粉丝的备注',
  `user_group_id`        varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户所在的分组ID',
  `user_tagid_list`      varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户被打上的标签ID列表',
  `user_subscribe_scene` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '用户关注的渠道来源 ADD_SCENE_SEARCH 公众号搜索 ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移 ADD_SCENE_PROFILE_CARD 名片分享 ADD_SCENE_QR_CODE 扫描二维码 ADD_SCENEPROFILE LINK 图文页内名称点击 ADD_SCENE_PROFILE_ITEM 图文页右上角菜单 ADD_SCENE_PAID 支付后关注 ADD_SCENE_OTHERS 其他',
  `user_qr_scene`        varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码扫码场景（开发者自定义）',
  `user_qr_scene_str`    varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维码扫码场景描述（开发者自定义）',
  PRIMARY KEY (`user_info_id`) USING BTREE,
  UNIQUE INDEX `index_openid` (`user_open_id`) USING BTREE COMMENT 'openid唯一索引',
  UNIQUE INDEX `index_phone_num` (`user_phone_num`) USING BTREE COMMENT 'phone num 唯一索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 10001
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '微信用户信息表'
  ROW_FORMAT = Dynamic;