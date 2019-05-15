DROP TABLE IF EXISTS `fei_kong_bao_messaging_wechat`.`wechat_user_message`;
CREATE TABLE IF NOT EXISTS `fei_kong_bao_messaging_wechat`.`wechat_user_message`
(
  user_message_id      bigint(20)  NOT NULL PRIMARY key AUTO_INCREMENT COMMENT '表ID',
  user_phone_num       DECIMAL(11) not null COMMENT '用户的电话号码',
  user_open_id         VARCHAR(60) not null comment '用户的openid',
  user_message_msg_id  VARCHAR(120) COMMENT '消息ID',
  user_message_status  VARCHAR(240) COMMENT '消息的发送状态',
  user_message_content text COMMENT '发消息的内容',
  create_time          TIMESTAMP   not NULL DEFAULT now() COMMENT '创建时间',
  last_update_time     TIMESTAMP   not NULL DEFAULT now() COMMENT '最后更改时间',
  UNIQUE INDEX `index_phone_num` (`user_phone_num`) USING BTREE COMMENT 'phone num 唯一索引'
) auto_increment = 10001;

CREATE TABLE `fei_kong_bao_messaging_wechat`.`Untitled`
(
  `user_message_id`      bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `user_phone_num`       decimal(11, 0)                                          NOT NULL COMMENT '用户的电话号码',
  `user_open_id`         varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户的openid',
  `user_message_msg_id`  varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '消息ID',
  `user_message_status`  varchar(240) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '消息的发送状态',
  `user_message_content` text CHARACTER SET utf8 COLLATE utf8_general_ci         NULL COMMENT '发消息的内容',
  `create_time`          timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_update_time`     timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '最后更改时间',
  PRIMARY KEY (`user_message_id`) USING BTREE,
  UNIQUE INDEX `index_phone_num` (`user_phone_num`) USING BTREE COMMENT 'phone num 唯一索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 10001
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;