package com.qt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qt.model.*;
import com.qt.utils.CheckUtil;
import com.qt.utils.MessageUtil;
import com.qt.utils.WeixinUtil;

import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by defore on 16/4/2.
 */
@Controller
@RequestMapping(value="weixin")
public class TestController {
    @RequestMapping(method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public void doGET(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();

        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = "application/xml;charset=UTF-8")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            //创建菜单
            AccessToken token = WeixinUtil.getAccessToken();
            String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
            int result = WeixinUtil.createMenu(token.getToken(), menu);

            //获取消息
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");

            String message = null;

            if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
                //关键字判断
                if ("1".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
                } else if ("2".equals(content)) {
                    message = MessageUtil.initNewsMessage(toUserName, fromUserName);
                } else if ("3".equals(content)) {
                    message = MessageUtil.initImageMessage(toUserName, fromUserName);
                } else if ("?".equals(content) || "？".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }

            } else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
                String eventType = map.get("Event");
                if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
                    //微信关注事件
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                } else if (MessageUtil.MESSAGE_CLICK.equals(eventType)) {
                    //如果是click事件,返回一个主菜单信息,没有回复
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                } else if (MessageUtil.MESSAGE_VIEW.equals(eventType)) {
                    //如果是view事件,返回一个跳转的地址信息,没有回复
                    String key = map.get("EventKey");
                    message = MessageUtil.initText(toUserName, fromUserName, key);
                }
            } else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)) {
                //地理事件
                String label = map.get("Label");
                message = MessageUtil.initText(toUserName, fromUserName, label);
            }
            out.print(message);
            System.out.println(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }


    @RequestMapping(value="test")
    public String getTest(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            String code = request.getParameter("code");
            System.out.println("code: "+ code);
            OauthToken oauthToken = WeixinUtil.getOauthToken(code);
            UserInfo user = WeixinUtil.getUserInfo(oauthToken.getAccess_token(), oauthToken.getOpenid());

            request.setAttribute("user", user);
            session.setAttribute("user", user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "test";
    }

    @RequestMapping(value="myLunch")
    public String myLunch(HttpServletRequest request) {
        try {
            String code = request.getParameter("code");
            System.out.println("code: "+ code);
            OauthToken oauthToken = WeixinUtil.getOauthToken(code);
            UserInfo user = WeixinUtil.getUserInfo(oauthToken.getAccess_token(), oauthToken.getOpenid());

            request.setAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "myLunch";
    }


    /*
    @ResponseBody
    @RequestMapping(value = "test")
    public JSONObject getTest(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            String code = request.getParameter("code");
            System.out.println("code: " + code);
            OauthToken oauthToken = WeixinUtil.getOauthToken(code);
            UserInfo user = WeixinUtil.getUserInfo(oauthToken.getAccess_token(), oauthToken.getOpenid());
            jsonObject = JSONObject.fromObject(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    */

    //测试返回json
    @ResponseBody
    @RequestMapping(value="getJson")
    public JSONObject getJson(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Map map = new HashMap();
        try {
            Image image = new Image();
            image.setMediaId("123456");
            map.put("result", image);
            jsonObject = JSONObject.fromObject(map);
            System.out.println(image.getMediaId());
            System.out.println(jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}