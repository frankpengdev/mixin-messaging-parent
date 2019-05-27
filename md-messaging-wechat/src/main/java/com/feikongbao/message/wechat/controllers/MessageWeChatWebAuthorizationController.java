package com.feikongbao.message.wechat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信网页授权
 *
 * @author zili.wang
 * @date 2019 /5/9 14:57
 */
@Controller
public class MessageWeChatWebAuthorizationController {


    @RequestMapping("/weChat/index")
    private ModelAndView modelAnd(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        return modelAndView;
    }


    /**
     * 微信网页授权请求地址,在用户未关注公众号时获取用户信息
     * 用户在公众号内进行网页跳转无需使用这个地址.
     *
     * @param request  the request
     * @param response the response
     * @author zili.wang
     * @date 2019/05/17 16:23:43
     */
    @RequestMapping("/weChat/oauth")
    private void modelAndView(HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx582d94ceb959eff5&redirect_uri=http%3A%2F%2F24t762094r.wicp.vip%2FweChat%2Findex&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
