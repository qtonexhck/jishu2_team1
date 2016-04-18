/**
 * Created by Administrator on 2016/4/3.
 */

// 检查当前时间是否为点餐时间，点餐时间为8:30-9:30，周一到周五
function checkTimeForFood(time) {
    var startOrder='08:30:00';//点餐开始时间
    var endOrder='09:30:00';//点餐结束时间
    var startLunch='12:00:00';//领餐开始时间
    var endLunch='12:30:00';//领餐结束时间
    if(time<startOrder)return 1;//未到点餐时间
    else if(time>=startOrder&&time<=endOrder)return 2;//点餐时间
    else if(time>endOrder&&time<startLunch)return 3;//点餐时间已过，领餐时间未到
    else if(time>=startLunch&&time<=endLunch)return 4;//领餐时间
    else return 5;//领餐时间已过
}

function isWeekday(getDateString) {
    var week=getDateString.substr(-1);
    if(week=='6'||week=='0'||week=='六'||week=='日'){
        return false;
    }
    return true;
}

function fromDateStringToDate(dateString) {
    var date=dateString.replace('年','-').replace('月','-').substr(0,10);
    return date;
}

//从服务器取得当期日期，返回“2016-03-03”格式日期
function getDate(async) {
    if(async==''||async==undefined)async=true;//默认为异步
    var url='http://'+getDomain()+'/TestController/getDate';
    var date=getDateAJAX(url,async);
    return date;
}

//从服务器取得当期日期，返回“2016年4月1日 星期五”格式日期
function getDateString(async) {
    if(async==''||async==undefined)async=true;//默认为异步
    var url='http://'+getDomain()+'/TestController/getDate2';
    var date=getDateAJAX(url,async);
    return date;
}

//从服务器取得当前时间，返回“11:11:11”格式时间
function getTime(async) {
    if(async==''||async==undefined)async=true;//默认为异步
    var url='http://'+getDomain()+'/TestController/getTime';
    var time=getDateAJAX(url,async);
    return time;
}

//ajax请求返回数据
function getDateAJAX(url,async) {
    if(async==''||async==undefined)async=true;//默认为异步
    url=url+'local';//开启本地时间
    var something='0000';
    //请求服务器时间
    $.ajax({
        type:'get',
        url:url,
        async:async,
        success:function (data) {
            something=data;
            return something;
        },
        error:function () {
            //获取本地时间
            if(url.match('getDate2')){
                something=getLocalDateString();
            }else if(url.match('getDate')){
                something=getLocalDate();
            }else if(url.match('getTime')){
                something=getLocalTime();
            }
            console.log(something+'失败');
            return something;
        }
    });

}

//从本地取得当期日期，返回“2016-03-03”格式日期
function getLocalDate() {
    var date=new Date();
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    var day=date.getDate();
    if(month < 10){
        month='0'+month;
    }
    if(day < 10){
        day='0'+day;
    }
    return year+'-'+month+'-'+day;
}

//从本地取得当期日期，返回“2016年4月1日 星期五”格式日期
function getLocalDateString() {
    var date=new Date();
    var year=date.getYear()+1900;
    var month=date.getMonth()+1;
    var day=date.getDate();
    var week=date.getDay();
    return year+'年'+month+'月'+day+'日 星期'+week;
}

//从本地取得当前时间，返回“11:11:11”格式时间
function getLocalTime() {
    var date=new Date();
    var hours=date.getHours();
    var minutes=date.getMinutes();
    var seconds=date.getSeconds();
    if(hours < 10)hours='0'+hours;
    if(minutes < 10)minutes='0'+minutes;
    if(seconds < 10)seconds='0'+seconds;
    return hours+':'+minutes+':'+seconds;
}

//清除弹窗提示，id为弹窗id，设置timeout时间后清除
function clearTips(id,timeout) {
    setTimeout(function () {
        $('#'+id).hide();
    },timeout);
}

//保存用户点餐数据
function setLocalStorage(foodItem,foodName,place) {
    if(localStorage){
        localStorage.setItem('foodItem',foodItem);
        localStorage.setItem('foodName',foodName);
        localStorage.setItem('place',place);
    }
}

//取得用户请求的点餐数据
function getLocalStorage(item) {
    var data=localStorage.getItem(item);

    return data?data:food[item];
}

//返回域名
function getDomain() {
    return '192.168.22.30:8080';
}

//测试用展示时间
function showTimeSelection() {
    $('#timeSelection').toggle();
}

//测试用选择时间
function selectTime(event) {
    var timeTarget=event.target;
    var time=$(timeTarget).text();
    var href = decodeURI(window.location);
    // href = href.split('?')[0];
    if(href.match('=')){
        if(href.match('time=')){
            href = href.split('?')[0];
            href=href+'?time='+time;
        }else{
            href=href+'&time='+time;
        }
    }else{
        href=href+'?time='+time;
    }
    // $('#timeSelection').hide();
    location.href=href;
}