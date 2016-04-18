/**
 * Created by Administrator on 2016/4/6.
 */
Lunchapp.constant("tips",{
    timeOut:"现在为非点餐时间，暂时无法点餐哦！点餐时间为：周一到周五8:30--9:30",
    serverClose:"服务器暂时已关闭，别玩手机了",
    serverError:"后台出现不可预知的错误，暂时拿不到数据",
    noChoosePlace:"请选择楼层",
    noChooseFood:"请选择菜式",
    payFail:"支付失败，该餐已点完或者可能是你的余额不足，请充值",
    changeFail:"支付失败，点餐时间已到或者该餐已点完",
    dataError:"数据错误"
});

Lunchapp.constant("url",{
    todayDishUrl:'http://192.168.31.86:8080/DishRecordController/getTodayDish',
    //dateUrl:"http://192.168.31.86:8080/TestController/getDate",
    //dateStringUrl:"http://192.168.22.30:8080/TestController/getDate2",
    //timeUrl:'http://192.168.22.30:8080/TestController/getTime',
    payUrl:'http://192.168.31.230:8080/order/getOrder',
    myFoodUrl:'http://192.168.31.230:8080/myOrder/getMyOrder',
    getAllTime:'http://192.168.31.86:8080/TestController/getAllTime',
    userInfo:'http://192.168.31.86:8080/TestController/getUserInfo'
});


