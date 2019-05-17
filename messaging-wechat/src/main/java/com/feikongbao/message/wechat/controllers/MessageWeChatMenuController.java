package com.feikongbao.message.wechat.controllers;

import com.feikongbao.message.wechat.exception.MessageWeChatException;
import com.feikongbao.message.wechat.service.MessageWeChatHandlerMenuService;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/menu/create")
    public String createMenu(HttpServletRequest request, HttpServletResponse response) throws MessageWeChatException {
        String menuText = request.getParameter("menuText");
        try {
            return menuService.menuCreate(menuText);
        } catch (MessageWeChatException | DocumentException e) {
            String msg = e.getMessage() == null ? e.getCause().getMessage():e.getMessage();
            LOGGER.error("create menu fail: "+msg);
            throw new MessageWeChatException("Menu creation failed, try again later");
        }
    }

    @RequestMapping("/menu/delete")
    public void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws MessageWeChatException {
        try {
            menuService.menuDelete();
        } catch (MessageWeChatException | DocumentException e) {
            String msg = e.getMessage() == null ? e.getCause().getMessage():e.getMessage();
            LOGGER.error("delete menu fail: "+msg);
            throw new MessageWeChatException("Menu deletion failed. Try again later");
        }
    }

}
