package com.testOrder.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.PcUser;
import com.testOrder.business.service.PcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 *
 * Created by 张三金 on 2016/4/1.
 */
@Controller
@RequestMapping(value = "/PcUserController")
public class PcUserController {

    @Autowired
    private PcUserService pcUserService;

    @ResponseBody
    @RequestMapping(value = "/getLoginUser",method = RequestMethod.POST)
    public Object getLoginUser(HttpServletRequest request,PcUser user){
        HashMap<String,Object> map = new HashMap<String, Object>();

//        System.out.println("====" + user);
//        user.setName(request.getParameter("username"));
//        user.setPasswd(request.getParameter("password"));
//        System.out.println(user + "zhangsan");

        // 如果没有该用户,返回不存在该用户名
        if(getUserByName(user.getName()).equals("[]")){
            map.put("code","0");
            map.put("msg","NO THIS USER");
            return map;
        }

        // 如果存在该用户，则同时通过用户和密码去取出来
        Criteria criteria = new Criteria();
        criteria.put("name",user.getName());
        criteria.put("passwd",user.getPasswd());
        map.put("user",pcUserService.selectByExample(criteria));

        if((map.get("user").toString()).equals("[]")) {
            map.put("code","1");
            map.put("msg","PASSWORD WRONG");
            return map;
        }

        map.put("code","2");
        return map;
    }

    /**
     * 根据用户的姓名来获取该用户
     * 通过该种方法返回，并没有把获取到的用户对象保存下来
     * @param username
     * @return 返回的是一个用户对象的字符串
     */
    @ResponseBody
    @RequestMapping(value = "/getuser")
    public Object getUserByName(String username){
        HashMap<String,Object> map = new HashMap<String, Object>();
        PcUser user = new PcUser();
        user.setName(username);

        Criteria criteria = new Criteria();
        criteria.put("name",user.getName());

        map.put("user",pcUserService.selectByExample(criteria));

        return map.get("user").toString();
    }

    public JSONObject parseMethod(String str,int code) {
        String str1 = "{\"code\":\""+code+"\",\"msg\":\""+str+"\"}";
        JSONObject jsonObject = JSON.parseObject(str);

        return jsonObject;
    }

}
