package com.testOrder.controller;


import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.Dish;
import com.testOrder.business.model.DishRecord;
import com.testOrder.business.service.DishRecordService;
import com.testOrder.business.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 菜单处理类
 * Created by 张三金 on 2016/4/5.
 */
@Controller
@RequestMapping(value = "/DishRecordController")
public class DishRecordController {
    @Autowired
    private DishRecordService dishRecordService;

    @Autowired
    private DishService dishService;

    // 获取今天的日期
    TestController testController = new TestController();
    String todayDate = testController.getDate("yyyyMMdd").toString();

    @ResponseBody
    @RequestMapping(value = "/getAllDish")
    public Object getDish(){
        HashMap<String,Object> map = new HashMap<String, Object>();
        DishRecord dishRecord = new DishRecord();


        Criteria criteria = new Criteria();
        map.put("dishRecord",dishRecordService.selectByExample(criteria));

        return map;
    }

    /**
     * 获取系统当天的菜单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTodayDish")
    public Object getTodayDish(HttpServletRequest request){

        String date = request.getParameter("date");
        HashMap<String,Object> map = new HashMap<String, Object>();


        DishRecord dishRecord = new DishRecord();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String today = df.format(new Date()).toString();//new Date()为获取当前系统时间


        Criteria criteria = new Criteria();

        criteria.put("creTime",today);
//        map.put("dishRecord",dishRecordService.selectByExample(criteria));
        map.put("dishRecord",dishRecordService.selectTodayDish(criteria));
        return map;
    }

    /**
     * 新增一个菜单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertMenu")
    public Object insertMenu(HttpServletRequest request) {

        String date = request.getParameter("date");

        String dishNameA = request.getParameter("inputA");
        String dishNameB = request.getParameter("inputB");
        String dishNameC = request.getParameter("inputC");

        DishRecord dishRecord = new DishRecord();
        Dish dish = new Dish();

        dish.setCook("A");
        dish.setDishName(dishNameA);
        dish.setDishPic(Integer.parseInt(todayDate + "01"));
        dishRecord.setDishId(Integer.parseInt(todayDate + "01"));
        dishRecord.setCreTime(date);
        dishRecordService.insert(dishRecord);
        dishService.insert(dish);

        dish = new Dish();
        dishRecord = new DishRecord();

        dish.setCook("B");
        dish.setDishName(dishNameB);
        dish.setDishPic(Integer.parseInt(todayDate + "02"));
        dishRecord.setDishId(Integer.parseInt(todayDate + "02"));
        dishRecord.setCreTime(date);
        dishRecordService.insert(dishRecord);
        dishService.insert(dish);

        dish = new Dish();
        dishRecord = new DishRecord();

        dish.setCook("C");
        dish.setDishName(dishNameC);
        dish.setDishPic(Integer.parseInt(todayDate + "03"));
        dishRecord.setDishId(Integer.parseInt(todayDate + "03"));
        dishRecord.setCreTime(date);
        dishRecordService.insert(dishRecord);
        dishService.insert(dish);

        return "dfd";
    }

    @ResponseBody
    @RequestMapping(value = "/updateMenu")
    public Object updateMenu(HttpServletRequest request) {
        String date = request.getParameter("date");

        String dishNameA = request.getParameter("inputA");
        String dishNameB = request.getParameter("inputB");
        String dishNameC = request.getParameter("inputC");

        DishRecord dishRecord = new DishRecord();
        Dish dish = new Dish();
        Criteria criteria = new Criteria();
        criteria.put("cook","A");
        criteria.put("dishPic",todayDate + "01");
        dish.setDishPic(Integer.parseInt(todayDate + "01"));
        dish.setDishId(Integer.parseInt(todayDate + "01"));
        dish.setDishName(dishNameA);
        dish.setCook("A");
        dishService.updateByExample(dish,criteria);

        criteria = new Criteria();
        criteria.put("cook","B");
        criteria.put("dishPic",todayDate + "02");
        dish.setDishPic(Integer.parseInt(todayDate + "02"));
        dish.setDishId(Integer.parseInt(todayDate + "02"));
        dish.setDishName(dishNameB);
        dish.setCook("B");
        dishService.updateByExample(dish,criteria);

        criteria = new Criteria();
        criteria.put("cook","C");
        criteria.put("dishPic",todayDate + "03");
        dish.setDishPic(Integer.parseInt(todayDate + "03"));
        dish.setDishId(Integer.parseInt(todayDate + "03"));
        dish.setDishName(dishNameC);
        dish.setCook("C");
        dishService.updateByExample(dish,criteria);

        return "UPDATE OK";
    }

    /**
     * 删除今天的菜单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteMenu")
    public Object deleteMenu(HttpServletRequest request){
        String date = request.getParameter("date");

        String dishNameA = request.getParameter("inputA");
        String dishNameB = request.getParameter("inputB");
        String dishNameC = request.getParameter("inputC");



        DishRecord dishRecord = new DishRecord();
        Dish dish = new Dish();
        Criteria criteria = new Criteria();
        criteria.put("cook","A");
        criteria.put("dishPic",todayDate + "01");
        dishService.deleteByExample(criteria);
        criteria = new Criteria();
        criteria.put("cook","B");
        criteria.put("dishPic",todayDate + "02");
        dishService.deleteByExample(criteria);
        criteria = new Criteria();
        criteria.put("cook","C");
        criteria.put("dishPic",todayDate + "03");
        dishService.deleteByExample(criteria);
        //
        criteria = new Criteria();
        criteria.put("dishId",todayDate + "01");
        criteria.put("creTime",date);
        dishRecordService.deleteByExample(criteria);

        criteria = new Criteria();
        criteria.put("dishId",todayDate + "02");
        criteria.put("creTime",date);
        dishRecordService.deleteByExample(criteria);

        criteria = new Criteria();
        criteria.put("dishId",todayDate + "03");
        criteria.put("creTime",date);
        dishRecordService.deleteByExample(criteria);

        return "DELETE OK";
    }
}
