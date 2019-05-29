package com.feikongbao.messaging.core;

import com.feikongbao.messaging.core.config.MessagingCoreConfig;
import com.feikongbao.messaging.core.service.MessagingCoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MessagingCoreConfig.class })
public class SenderTest {

	@Autowired
	private MessagingCoreService messagingCoreService;

	/** 测试保存数据库 **/
	@Test
	public void testStorage(){
		Long userId = 56789L;
		String uuid = "test";
		String exchange = "test";
		String routingKey = "test";
		int replyCode = 999;
		String replyText = "test";
		String messageJson = "test";
		messagingCoreService.saveReturnedMessageStorage(userId,uuid,exchange,routingKey,replyCode,replyText,messageJson);
	}
}

