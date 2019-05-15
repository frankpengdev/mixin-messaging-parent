package com.feikongbao.message.wechat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信网页授权
 *
 * @author zili.wang
 * @date 2019 /5/9 14:57
 */
@Controller
public class MessageWeChatWebAuthorizationController {

    @RequestMapping("/weChat/index")
    private ModelAndView modelAndView(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        return modelAndView;
    }


}
