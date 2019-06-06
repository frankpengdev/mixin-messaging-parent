
DROP TABLE IF EXISTS `messaging_core`;

CREATE TABLE `messaging_core` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `message_uuid` varchar(200) NOT NULL DEFAULT '' COMMENT '消息随机id',
  `exchange` varchar(200) DEFAULT '' COMMENT '交换机',
  `routing_key` varchar(200) DEFAULT '' COMMENT '路由键',
  `reply_code` int(1) DEFAULT '0' COMMENT '失败状态码',
  `reply_test` varchar(1500) DEFAULT '' COMMENT '失败原因',
  `message_send` varchar(1500) NOT NULL DEFAULT '' COMMENT '消息',
  `status` int(1) DEFAULT '0' COMMENT '数据库数据状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=623 DEFAULT CHARSET=utf8 COMMENT = '消息发送失败存储表';
