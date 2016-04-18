package com.testOrder.controller;


import com.testOrder.business.model.WxUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by 张三金 on 2016/4/8.
 */
@ResponseBody
@RequestMapping(value = "/WxUserController")
public class WxUserController {

    /**
     * 保存微信用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/saveWxUser")
    public Object saveWxUser(HttpServletRequest request) {

        HashMap<String,Object> map = new HashMap<String,Object>();
        WxUser wxUser = new WxUser();

        String nickname = request.getParameter("nickname");
        String openid = request.getParameter("openid");
        String headimgurl = request.getParameter("headimgurl");

        wxUser.setNickname(nickname);
        wxUser.setOpenId(openid);
        wxUser.setHeadimgurl(headimgurl);

        map.put("wxUser",wxUser);

        System.out.println("=====zhangsanjin======" + map);

        return map;
    }
}
