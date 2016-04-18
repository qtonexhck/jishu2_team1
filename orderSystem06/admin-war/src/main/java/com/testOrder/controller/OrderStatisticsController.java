package com.testOrder.controller;

import com.testOrder.business.model.Criteria;
import com.testOrder.business.service.ConsumptionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 统计订单信息
 * Created by 张三金 on 2016/4/5.
 */
@Controller
@RequestMapping(value = "/OrderStatisticsController")
public class OrderStatisticsController {

    @Autowired
    private ConsumptionRecordService consumptionRecordService;

    /**
     * 获取订单详情信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderInfo")
    public Object getOrderInfo(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<String, Object>();

        String consumptionDate = request.getParameter("consumptionDate");
        String place = request.getParameter("place");

        Criteria criteria = new Criteria();
        criteria.put("time",consumptionDate);
        criteria.put("place",place);

        map.put("orderInfo",consumptionRecordService.selectOrderByPlace(criteria));
//        map.put("orderInfo",consumptionRecordService.selectByExample(criteria));

        return map;
    }

   @ResponseBody
   @RequestMapping(value = "/getOrderInfo01")
   public Object getOrderInfo01(HttpServletRequest request) {
       HashMap<String,Object> map = new HashMap<String, Object>();

       String consumptionDate = request.getParameter("consumptionDate");

       Criteria criteria = new Criteria();
       criteria.put("time",consumptionDate);
       criteria.put("place","17楼");
       map.put("floor17",consumptionRecordService.selectOrderByPlace(criteria));

       criteria.put("place","18楼");
       map.put("floor18",consumptionRecordService.selectOrderByPlace(criteria));

       criteria.put("place","5");
       map.put("floor05",consumptionRecordService.selectOrderByPlace(criteria));

       return map;
   }

    /**
     * 领取详情
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMealInfo")
    public Object getMealInfo(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<String, Object>();

        String time = request.getParameter("time");
        Criteria criteria = new Criteria();
        criteria.put("time",time);
        map.put("getMealInfo",consumptionRecordService.selectGetMealInfo(criteria));

        return map;
    }

}
