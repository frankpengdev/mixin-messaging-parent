package com.feikongbao.messaging.email.client.controller;

import com.feikongbao.messaging.email.api.entity.MailEntity;
import com.feikongbao.messaging.email.client.sender.SenderMailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/6/13 16:36
 **/
@RestController
@RequestMapping(value = "/mail")
public class TestController {

    @Autowired
    private SenderMailService senderMailService;

    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public TestResponse sendTest(MailEntity mailEntity){
        if (mailEntity == null || StringUtils.isBlank(mailEntity.getFrom()) || CollectionUtils.isEmpty(mailEntity.getTo())){
            return new TestResponse(500, "参数错误");
        }
        try {
            senderMailService.sendMail(mailEntity);
            return new TestResponse(200, "发送成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new TestResponse(500, "发送失败");
    }
}
