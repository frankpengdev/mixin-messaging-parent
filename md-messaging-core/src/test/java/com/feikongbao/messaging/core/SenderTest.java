package com.feikongbao.messaging.core;

import com.feikongbao.messaging.core.config.MessagingCoreConfig;
import com.feikongbao.messaging.core.constants.AbstractMessagingConstants;
import com.feikongbao.messaging.core.sender.SenderMessage;
import com.feikongbao.messaging.core.service.ReturnedMessageStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * 测试类
 * @author Administrator
 *
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MessagingCoreApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MessagingCoreConfig.class })
public class SenderTest {

	@Autowired
	private SenderMessage<TestEntity> directSenderObject;

	@Autowired
	private ReturnedMessageStorageService returnedMessageStorageService;

	@Test
	public void testStorage(){
		Long userId = 56789L;
		String uuid = "test";
		String exchange = "test";
		String routingKey = "test";
		int replyCode = 999;
		String replyText = "test";
		String messageJson = "test";
		returnedMessageStorageService.saveReturnedMessageStorage(userId,uuid,exchange,routingKey,replyCode,replyText,messageJson);
	}

	/**
	 * sendDirectObject
	 * @return
	 * @throws Exception
	 */
	@Test
	public void sendDirectObject() throws Exception {
		for (int i = 1; i < 6; i++){
			Thread.sleep(1000);
			TestEntity boy = new TestEntity();
			boy.setId(i);
			boy.setUserName("direct.log.info obj : " + i);
			boy.setCreateTime(new Date());
			Long userId = 789L;
			directSenderObject.sendMessage(AbstractMessagingConstants.DIRECT_MQ_EXCHANGE_TEST,AbstractMessagingConstants.DIRECT_MQ_ROUTINGKEY_TEST,boy,userId);
		}
	}

	/**
	 * 测试fanout
	 * @return
	 * @throws Exception
	 */
	@Test
	public void sendFanoutObject() throws Exception {
		for (int i = 1; i < 5; i++){
			Thread.sleep(1000);
			TestEntity boy = new TestEntity();
			boy.setId(i);
			boy.setUserName("fanout.log.info obj : " + i);
			boy.setCreateTime(new Date());
			Long userId = 789L;
			directSenderObject.broadcastMessage(AbstractMessagingConstants.FANOUT_MQ_EXCHANGE_TEST,boy,userId);
		}
	}

	/**
	 * 测试topic
	 * @return
	 * @throws Exception
	 */
	@Test
	public void sendTopicObject() throws Exception {
		for (int i = 1; i < 6; i++) {
			Thread.sleep(1000);
			TestEntity boy = new TestEntity();
			boy.setId(678);
			boy.setUserName("topic.log.info obj : " + i);
			boy.setCreateTime(new Date());
			Long userId = 789L;
			directSenderObject.sendMessage(AbstractMessagingConstants.TOPIC_MQ_EXCHANGE_TEST, AbstractMessagingConstants.TOPIC_MQ_ROUTINGKEY_SENDER_TEST, boy, userId);
		}
	}
}

