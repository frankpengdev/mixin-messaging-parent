package com.mixin.messaging.wechat.service;

import com.mixin.messaging.wechat.config.MessageWeChatConfiguration;
import com.mixin.messaging.wechat.exception.MessageWeChatException;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


/**测试 /service/MessageWeChatHandlerMenuService
 * @author Jinjing
 * @date 2019/6/11 10:47
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MessageWeChatConfiguration.class)
public class TestWeChatMenu {


    @Autowired
    private MessageWeChatHandlerMenuService menuService;

    /**
     * 创建一个正常的菜单
     * @throws MessageWeChatException
     * @throws DocumentException
     */

    @Test
    public void testCreateMenu()throws MessageWeChatException, DocumentException {

        String menuString = "{\n" +
                "     \"button\":[\n" +
                "     {\n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"费控宝\",\n" +
                "          \"key\":\"V1001_TODAY_MUSIC\"\n" +
                "      },\n" +
                "   {\n" +
                "   \"type\":\"click\",\n" +
                "               \"name\":\"商城\",\n" +
                "               \"key\":\"V1001_FEIKONGBAO_Mall\"\n" +
                "   },\n" +
                "      {\n" +
                "           \"name\":\"菜单\",\n" +
                "           \"sub_button\":[\n" +
                "           {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"个人中心\",\n" +
                "               \"url\":\"http://24t762094r.wicp.vip/weChat/index\"\n" +
                "            },{\n" +
                "               \"type\":\"click\",\n" +
                "               \"name\":\"赞一下我们\",\n" +
                "               \"key\":\"V1001_GOOD\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }";
        // 先删除已经创建的菜单
        String deleteResult = menuService.menuDelete();
        assertThat(deleteResult, containsString("ok"));

        // 新建菜单
        String result = menuService.menuCreate(menuString);
        Assert.assertEquals("ok", result );

    }

    /**
     * 创建一个名字很长的菜单 —— 异常：invalid button name size hint
     * @throws MessageWeChatException
     * @throws DocumentException
     */
    @Test
    public void testCreateLongNameMenu()throws MessageWeChatException, DocumentException {

        String menuString = "{\n" +
                "     \"button\":[\n" +
                "     {\n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"费控宝\",\n" +
                "          \"key\":\"V1001_TODAY_MUSIC\"\n" +
                "      },\n" +
                "   {\n" +
                "   \"type\":\"click\",\n" +
                "               \"name\":\"费控宝商城\",\n" +
                "               \"key\":\"V1001_FEIKONGBAO_Mall\"\n" +
                "   },\n" +
                "      {\n" +
                "           \"name\":\"不允许建一个名字很长的菜单\",\n" +
                "           \"sub_button\":[\n" +
                "           {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"个人中心\",\n" +
                "               \"url\":\"http://24t762094r.wicp.vip/weChat/index\"\n" +
                "            },{\n" +
                "               \"type\":\"click\",\n" +
                "               \"name\":\"赞一下我们\",\n" +
                "               \"key\":\"V1001_GOOD\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }";
            String result = menuService.menuCreate(menuString);
            assertThat(result, containsString("invalid button name size hint"));

    }

}
