package com.mixin.messaging.wechat.controllers;

import com.mixin.messaging.wechat.exception.MessageWeChatException;
import com.mixin.messaging.wechat.service.MessageWeChatHandlerMenuService;
import com.mixin.messaging.wechat.util.MessageWeChatHelpUtil;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建菜单
 *
 * @author zili.wang
 * @date 2019/5/14 16:34
 */
@Controller
public class MessageWeChatMenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageWeChatMenuController.class);

    @Autowired
    private MessageWeChatHandlerMenuService menuService;

    @RequestMapping(value = "/menu/create", method = RequestMethod.POST)
    @ResponseBody
    public String createMenu(HttpServletRequest request, @RequestBody String menuText, HttpServletResponse response) throws MessageWeChatException {
        try {
            String menuTextFormat = MessageWeChatHelpUtil.formatJson(menuText);
            return menuService.menuCreate(menuTextFormat);
        } catch (DocumentException | IOException e) {
            String msg = e.getMessage() == null ? e.getCause().getMessage() : e.getMessage();
            LOGGER.error("create menu fail: " + msg);
            throw new MessageWeChatException("Menu creation failed, try again later");
        }
    }

    @RequestMapping("/menu/delete")
    public void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws MessageWeChatException {
        try {
            menuService.menuDelete();
        } catch (MessageWeChatException | DocumentException e) {
            String msg = e.getMessage() == null ? e.getCause().getMessage() : e.getMessage();
            LOGGER.error("delete menu fail: " + msg);
            throw new MessageWeChatException("Menu deletion failed. Try again later");
        }
    }

}
