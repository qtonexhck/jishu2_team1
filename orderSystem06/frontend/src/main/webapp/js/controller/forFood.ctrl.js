Lunchapp.controller('forFoodCtrl',function ( $rootScope,$scope,$http,tips,url) {
    $rootScope.nav='canteen';

    //获取自定义时间，测试用
    var href = decodeURI(window.location);
    if(href.match('time=')){//这是一个测试地址
        $scope.hasTime=href.split('?').pop().split('=').pop();
        console.log($scope.hasTime);
    }
    if(href.match('open_id=')){//这是一个带有微信openID的地址
        $scope.open_id=href.split('?').pop().split('&')[0].split('=').pop();
        sessionStorage.setItem('open_id',$scope.open_id);
    }else if(sessionStorage.getItem('open_id')){//从本地查找open_id
        $scope.open_id=sessionStorage.getItem('open_id');
    }

    $('#payTips').hide();//隐藏支付提示
    $('#payButton').addClass('disabled');//初始化，不能支付
    $('#warmTips').hide();//点餐时间，隐藏温馨提示

    //----------------------获得服务器上的日期和时间-------------
    $http({
        url:url.getAllTime,
        method:'get'
    }).success(function (data) {
        if(data==undefined||data['dateString']==undefined){
            $scope.warmTips=tips.serverError;
            $('#warmTips').show();//显示温馨提示，数据出错
            return;
        }
        $scope.today=data['dateString'];
        if($scope.hasTime==undefined){
            $scope.hasTime=data['time'];
        }
        if(checkTimeForFood($scope.hasTime)!=2){//检查是否是点餐时间
            $scope.warmTips=tips.timeOut;
            $('#warmTips').show();//显示温馨提示，非点餐时间
            return;
        }
    }).error(function () {
        $scope.warmTips=tips.serverClose;
        $('#warmTips').show();//显示温馨提示，服务器出现问题
    });

    //----------------------获取菜单列表-------------
    $http({
        url:url.todayDishUrl,
        method:'get'
    }).success(function (data) {
        if(data['dishRecord'] == undefined ||
            data['dishRecord'][0] == undefined ||
            data['dishRecord'][0]['dish'] == undefined ||
            data['dishRecord'][0]['dish']['dishName'] == undefined){//防止后台返回的数据出现异常
            $scope.warmTips=tips.serverError;
            $('#warmTips').show();//显示温馨提示，数据出错
            clearTips('warmTips',2000);
            return;
        }else{//有数据返回
            $scope.list=data["dishRecord"];
            //A餐
            $scope.foodA=$scope.list[getFoodIndex('A')]['dish']['dishName'];
            //B餐
            $scope.foodB=$scope.list[getFoodIndex('B')]['dish']['dishName'];
            //C餐
            $scope.foodC=$scope.list[getFoodIndex('C')]['dish']['dishName'];
        }
    }).error(function () {
        $scope.warmTips=tips.serverClose;
        $('#warmTips').show();//显示温馨提示，服务器出现问题
    });

    //选择5、17、18楼
    $scope.selectPlace = function (place) {
        if($scope.list == undefined)return;//没有数据，不能选择
        $scope.selectedPlace=place;//记录选择的楼层
        $scope.place=place;//显示选中按钮
    }

    //选择A、B、C餐
    $scope.selectThis = function (type) {
        if($scope.list == undefined)return;//没有数据，不能选择
        var index=getFoodIndex(type);
        console.log('找到的索引是：'+index);
        if(index==undefined){
            $scope.warmTips='后台那个傻X，数据库设计有问题';
            $('#warmTips').show();
        }
        $scope.selectionType=type;//记录选择的餐类型
        $scope.mySelectionName=$scope.list[index]['dish']['dishName'];//记录选择的食物的名称
        $scope.check=type;//显示选中按钮
        if($('#payButton').hasClass('disabled')){
            $('#payButton').removeClass('disabled');
        }
        if(!$('#payButton').hasClass('enabled')){
            $('#payButton').addClass('enabled');
        }

    }

    //根据数组key的值拿到索引
    function getFoodIndex(value) {
        for(var item in $scope.list){//得到餐类型所在数组的索引
            if($scope.list[item]==undefined||$scope.list[item]['dish']==undefined||$scope.list[item]['dish']['cook'] == undefined){
                $scope.warmTips=tips.dataError;
                $('#warmTips').show();
                clearTips('warmTips',2000);
            }
            if($scope.list[item]['dish']['cook']==value){
                return item;
            }
        }
    }

    //取消选择
    $scope.cancel = function () {
        $('.foodBox').find('.selectFood').removeClass('selection');
        $('#payButton').removeClass('enabled');
        $('#payButton').addClass('disabled');//设置为不可点状态
        $scope.selectionType = '';
        $scope.mySelectionName = '';
        $scope.check='';
        $scope.selectedPlace='';
        $scope.place='';
    }

    //确定选择
    $scope.confirm = function () {
        if( $scope.selectionType==''|| $scope.selectionType==undefined){//未选择菜式
            $scope.warmTips=tips.noChooseFood;
            $('#warmTips').show();
            clearTips('warmTips',2000);
            return;
        }
        if( $scope.selectedPlace==''|| $scope.selectedPlace==undefined){//未选择菜式
            $scope.warmTips=tips.noChoosePlace;
            $('#warmTips').show();
            clearTips('warmTips',2000);
            return;
        }
        $http({//请求服务器时间
            url:url.getAllTime,
            method:'get'
        }).success(function (data) {
            var time='';
            if(data['time']==undefined){
                $scope.warmTips=tips.serverError;
                $('#warmTips').show();//显示温馨提示，数据出错
                return;
            }
            //如果有自定义时间，不鸟服务器
            if($scope.hasTime){
                time=$scope.hasTime;
            }else{
                time=data['time'];
            }

            if(checkTimeForFood(time)!=2){//检查是否是点餐时间
                $scope.warmTips=tips.timeOut;
                $('#warmTips').show();//显示温馨提示，非点餐时间
                return;
            }
            $('#payTips').show();//可以点餐
        }).error(function () {
            $scope.warmTips=tips.serverClose;
            $('#warmTips').show();//显示温馨提示，服务器出现问题
        });
    }

    //重新选择
    $scope.back = function () {
        $('#payTips').hide();
    }

    //确认支付
    $scope.pay = function () {
        var index=getFoodIndex($scope.selectionType);
        var dishId=$scope.list[index]['dishId'];
        if($scope.open_id==undefined||$scope.open_id==''){
            $scope.open_id='testaaaaaaa';//给一个测试open_id
        }
        $http({
            url:url.payUrl,
            method:'get',
            params:{
                open_id:$scope.open_id,
                dish_id:dishId,
                place:$scope.selectedPlace
            }
        }).success(function (data) {
            var status=data['code'];
            console.log('状态：'+status);
            switch (status){
                case '0':{// 支付失败
                    $scope.warmTips=tips.payFail;
                    $('#warmTips').show();
                    clearTips('warmTips',2000);
                }break;
                case '1':
                case '2':{//支付或修改成功，跳转到成功页面
                    setLocalStorage($scope.selectionType,$scope.mySelectionName,$scope.selectedPlace);//保存用户点餐信息
                    location.href = '#/payStatus?status='+status+'&i='+$scope.selectionType+'&name='+$scope.mySelectionName+'&place='+$scope.selectedPlace;
                }break;
                case '3':{//修改失败
                    $scope.warmTips=tips.changeFail;
                    $('#warmTips').show();
                    clearTips('warmTips',2000);
                }break;
                default:{
                    // 支付失败
                    $scope.warmTips=tips.serverClose;
                    $('#warmTips').show();
                    clearTips('warmTips',2000);
                }break;
            }
        }).error(function () {
            // 支付失败，跳转到失败页面
            $scope.warmTips=tips.serverClose;
            $('#warmTips').show();
            clearTips('warmTips',2000);
        });
    }
});