DROP TABLE IF EXISTS `test_company`.`messaging_wechat_user_message`;
CREATE TABLE `test_company`.`messaging_wechat_user_message`
(
  `user_message_id`      bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `user_phone_num`       decimal(11, 0)                                          NOT NULL COMMENT '用户的电话号码',
  `user_open_id`         varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL COMMENT '用户的openid',
  `user_message_msg_id`  varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '消息ID',
  `user_message_status`  varchar(240) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '消息的发送状态',
  `user_message_content` text CHARACTER SET utf8 COLLATE utf8_general_ci         NULL COMMENT '发消息的内容',
  `create_time`          timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_update_time`     timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '最后更改时间',
  `err_message`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '返回信息',
  PRIMARY KEY (`user_message_id`) USING BTREE,
  INDEX `index_phone_num` (`user_phone_num`) USING BTREE COMMENT 'phone num 唯一索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 10021
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;