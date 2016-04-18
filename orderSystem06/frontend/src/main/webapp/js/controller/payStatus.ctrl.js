/**
 * Created by Administrator on 2016/3/31.
 */
Lunchapp.controller('payStatusCtrl',function ( $rootScope,$scope,$http,url) {
    $rootScope.nav='canteen';
    $('title').html('你的午餐');
    var href = decodeURI(window.location);
    var foodData = href.split('?').pop().split('&');
    var status=foodData[0].split('=').pop();
    $scope.foodItem = foodData[1].split('=').pop();
    $scope.foodName = '《'+foodData[2].split('=').pop().replace(' ','+')+'》';
    $scope.place = foodData[3].split('=').pop()+'楼：';
    $http({//请求服务器日期
        url:url.getAllTime,
        method:'get'
    }).success(function (data) {
        if(data['dateString']==undefined){
            $scope.warmTips=tips.serverError;
            $('#warmTips').show();//显示温馨提示，数据出错
            return;
        }
        $scope.today=data['dateString'];
    }).error(function () {
        $scope.warmTips=tips.serverClose;
        $('#warmTips').show();//显示温馨提示，服务器出现问题
    });
    //status：0支付失败，1支付成功，2修改餐单
    switch (status){
        case '0':{
            $scope.payStatus='支付失败';
            $scope.statusButton='充值';
            $('#statusIcon').addClass('failIcon');
            var tips='<p class="chineseTips"><i class="tipsIcon icon"></i>你的余额已不足6元，请及时充值！</p>';
            $('#menu').append(tips);
        }break;
        case '1':{
            $scope.payStatus='支付成功';
            $scope.statusButton='修改餐单';
            $('#statusIcon').addClass('successIcon');
        }break;
        case '2':{
            $scope.payStatus='修改成功';
            $scope.statusButton='任性再改';
            $('#statusIcon').addClass('successIcon');
        }break;
    }

    $scope.doSomethings = function () {
        if(status==0){
            location.href = '#/user';
        }else{
            location.href = '#/forFood';
        }
    }
});

