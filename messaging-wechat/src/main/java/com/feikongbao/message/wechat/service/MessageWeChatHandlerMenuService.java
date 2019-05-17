package com.feikongbao.message.wechat.service;

import com.feikongbao.message.wechat.exception.MessageWeChatException;
import com.feikongbao.message.wechat.util.MessageWeChatHelpUtil;
import com.feikongbao.message.wechat.util.MessageWeChatRequestUrlEnum;
import com.feikongbao.message.wechat.util.WeChatHttpStatusCodeEnum;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 自定义菜单的创建和删除
 *
 * @author zili.wang
 * @date 2019/5/15 15:47
 */
@Service
public class MessageWeChatHandlerMenuService {

    private static final String MENU_CREATE_URL = MessageWeChatRequestUrlEnum.WECHAT_MENU_CREATE_URL.getDescription();
    private static final String MENU_DELETE_URL = MessageWeChatRequestUrlEnum.WECHAT_MENU_DELETE_URL.getDescription();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageWeChatHandlerWeChatDataService weChatDataService;

    /**
     * Menu create .
     *
     * @param menuString the menu string
     * @return the string
     * @throws MessageWeChatException the message we chat exception
     * @throws DocumentException      the document exception
     * @author zili.wang
     * @date 2019/05/15 16:53:44
     */
    public String menuCreate(String menuString) throws MessageWeChatException, DocumentException {
        String accessToken = weChatDataService.getWeChatAccessTokenFromCache();
        String responseMsg = restTemplate.postForObject(MENU_CREATE_URL, menuString, String.class, accessToken);
        return handleResponseMsg(responseMsg);
    }

    /**
     * Menu delete.
     *
     * @return the string
     * @throws MessageWeChatException the message we chat exception
     * @throws DocumentException      the document exception
     * @author zili.wang
     * @date 2019/05/15 17:01:45
     */
    public String menuDelete() throws MessageWeChatException, DocumentException {
        String accessToken = weChatDataService.getWeChatAccessTokenFromCache();
        String responseMsg = restTemplate.getForObject(MENU_DELETE_URL, String.class, accessToken);
        return handleResponseMsg(responseMsg);
    }

    private String handleResponseMsg(String responseMsg) throws DocumentException {
        Map<String, String> map = MessageWeChatHelpUtil.xml2Map(responseMsg);
        boolean errCode = WeChatHttpStatusCodeEnum.OK.getCode().equals(map.get("errcode"));
        if (!errCode) {
            return map.get("errmsg");
        }
        return "success";
    }
}
