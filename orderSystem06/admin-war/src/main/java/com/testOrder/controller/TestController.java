package com.testOrder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by 张三金 on 2016/4/5.
 */
@Controller
@RequestMapping(value = "/TestController")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/getDate")
    public Object getDate(String str){
        if(str == null){
            str = "yyyy-MM-dd";
        }
        SimpleDateFormat df = new SimpleDateFormat(str);//设置日期格式
        String today = df.format(new Date()).toString();//new Date()为获取当前系统时间
        return today;
    }

    @ResponseBody
    @RequestMapping(value = "/getTime")
    public Object getTime(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        String time = df.format(new Date()).toString();//new Date()为获取当前系统时间
        return time;
    }

    @ResponseBody
    @RequestMapping(value = "/getDate2",method = RequestMethod.GET)
    public Object getDate2(HttpServletRequest request, HttpServletResponse response) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 EEEE");//设置日期格式
        String today = df.format(new Date()).toString();//new Date()为获取当前系统时间
        response.setCharacterEncoding("UTF-8");

        return today;
    }

    @ResponseBody
    @RequestMapping(value = "/myTest")
    public Object myTest() {

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("name","zhangsan");
        map.put("age","11");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllTime")
    public Object getAllTime() {

        HashMap<String,Object> map = new HashMap<String,Object>();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date()).toString();

        df = new SimpleDateFormat("yyyy年MM月dd EEEE");
        String dateString = df.format(new Date()).toString();

        df = new SimpleDateFormat("HH:mm:ss");
        String time = df.format(new Date()).toString();

        map.put("date",date);
        map.put("dateString",dateString);
        map.put("time",time);

        return map;
    }
}
