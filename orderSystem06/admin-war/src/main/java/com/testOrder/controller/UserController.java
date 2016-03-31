package com.testOrder.controller;

import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.OrderUser;
import com.testOrder.business.service.OrderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by 17842 on 2016/3/29.
 */
@Controller
@RequestMapping(value = "/test")
public class UserController {

    @Autowired
    private OrderUserService orderUserService;
    @ResponseBody
    @RequestMapping(value = "/getUserInfo")
    public Object getUserInfo(){
        HashMap<String,Object> map = new HashMap<String,Object>();
        OrderUser user = new OrderUser();

        Criteria criteria = new Criteria();
        map.put("user", orderUserService.selectByExample(criteria));

        return map;
    }
}
