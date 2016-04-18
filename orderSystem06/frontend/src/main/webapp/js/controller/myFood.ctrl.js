Lunchapp.controller('myFoodCtrl',function ( $rootScope,$scope,$http,tips,url) {
    $rootScope.nav='myLunch';

    var href = decodeURI(window.location);
    if(href.match('time')){//这是一个测试地址
        $scope.hasTime=href.split('?').pop().split('=').pop();
        console.log($scope.hasTime);
    }
    if(href.match('open_id=')){//这是一个带有微信openID的地址
        $scope.open_id=href.split('?').pop().split('&')[0].split('=').pop();
        sessionStorage.setItem('open_id',$scope.open_id);
    }else if(sessionStorage.getItem('open_id')){//从本地查找open_id
        $scope.open_id=sessionStorage.getItem('open_id');
    }

    $('#warmTips').hide();//点餐时间，隐藏温馨提示

    //----------------------获得服务器上的日期和时间-------------
    $http({
        url:url.getAllTime,
        method:'get'
    }).success(function (data) {
        //----------------------记录从服务器上拿到的时间-------------
        if(data['dateString']!=undefined){
            $scope.today=data['dateString'];
            $scope.date=data['date'];
            if($scope.hasTime==undefined){
                $scope.hasTime=data['time'];
            }
        }else{
            console.log('时间出错');
            console.log(data);
            $scope.warmTips=tips.serverError;
            $('#warmTips').show();//显示温馨提示，数据出错
            return;
        }

        //----------------------判断当前时间的可否点餐情况-------------
        if(!isWeekday($scope.today)){//如果是周六日，不能点餐
            $scope.warmTips=tips.timeout;
            $('#warmTips').show();//显示温馨提示，非点餐时间
            return;
        }

        if($scope.open_id==undefined||$scope.open_id==''){
            $scope.open_id='testaaaaaaa';//如果没有open_id,给个测试用
        }

        //----------------------获取用户的点餐信息-------------
        $http({
            url:url.myFoodUrl,
            method:'get',
            params:{
                open_id:$scope.open_id
            }
        }).success(function (foodData) {
            console.log(foodData['ConsumptionRecord'][0]);
            var code='';//获取用户的点餐状态码
            if(foodData['code']!=undefined){
                code=foodData['code'];//这里状态码为0，表示用户还没点餐
            }else if(foodData['ConsumptionRecord']!=undefined){
                $scope.myFood=foodData['ConsumptionRecord'][0];
                code=$scope.myFood['consumed_st'];//这里状态码为1或2，1点餐但未领取，2点餐且已领取
            }else {
                console.log('数据出错');
                console.log(foodData);
                $scope.warmTips=tips.serverError;
                $('#warmTips').show();//显示温馨提示，数据出错
                return;
            }

            //--------------------显示不同点餐状态码的点餐信息-------------
            switch (code){
                case '0':{
                    //没有我的今天菜单
                    $scope.foodItem='无';
                    $scope.foodName='《无菜式》';
                    $scope.place='无楼层：';
                }break;
                case '1':
                case '2':{
                    //成功返回我今天的菜单
                    if(localStorage.getItem('foodItem')){
                        $scope.foodItem=localStorage.getItem('foodItem');
                        $scope.foodName='《'+localStorage.getItem('foodName')+'》';
                        $scope.place=localStorage.getItem('place')+'楼：';
                    }else{
                        $scope.foodItem=$scope.myFood['cook'];
                        $scope.foodName='《'+$scope.myFood['dish_name']+'》';
                        $scope.place=$scope.myFood['place']+'楼：';
                    }
                }break;
                default:{
                    $scope.foodItem='O';
                    $scope.foodName='《九大簋》';
                    $scope.place='十八层地狱：';
                    console.log('获取状态码不成功');
                    console.log(code);
                    $scope.warmTips=tips.serverError;
                    $('#warmTips').show();//显示温馨提示，数据出错
                }break;
            }

            //--------------------显示不同时间下底部按钮的不同提示和功能-------------
            switch (checkTimeForFood($scope.hasTime)){
                case 0:
                case 1:{
                    //状态1，点餐时间未到
                    $scope.statusCode=1;
                    $scope.action='未能领餐';
                    $scope.status='时间未到';
                    $('.leftButton').addClass('disabled');
                }break;
                case 2:{
                    //状态2，点餐时间
                    $scope.statusCode=2;
                    $scope.action='点餐||更改';
                    $scope.status='可以点餐';
                    $('.leftButton').addClass('enabled');
                }break;
                case 3:{
                    //状态3，点餐时间已过，但领餐时间未到
                    $scope.statusCode=3;
                    $scope.action='未能领取';
                    $scope.status='未到中午';
                    $('.leftButton').addClass('disabled');
                }break;
                case 4:{
                    if(code==1){
                        //状态4，领餐时间，未领餐
                        $scope.statusCode=4;
                        $scope.action='领餐';
                        $scope.status='领餐时间';
                        $('.leftButton').addClass('enabled');
                    }else if(code==2){
                        //状态5，领餐时间，已领餐
                        $scope.statusCode=5;
                        $scope.action='已领餐';
                        $scope.status='领餐时间';
                        $('.leftButton').addClass('disabled');
                    }else{
                        $scope.warmTips=tips.serverError;
                        $('#warmTips').show();//显示温馨提示，数据出错
                        return;
                    }
                }break;
                case 5:{
                    //状态6，领餐时间已过
                    $scope.statusCode=6;
                    $scope.action='不能领取';
                    $scope.status='已过期';
                    $('.leftButton').addClass('disabled');
                }break;
                default:break;
            }
        }).error(function () {
            $scope.warmTips=tips.serverClose;
            $('#warmTips').show();//显示温馨提示，服务器出现问题
        });
    }).error(function () {
        $scope.warmTips=tips.serverClose;
        $('#warmTips').show();//显示温馨提示，服务器出现问题
    });
    //----------------------结束----------------

    //响应用户的点击，不同情况有不同功能
    $scope.doSomethings = function () {
        switch ($scope.statusCode){
            case 0:{
            }break;
            case 1:{
            }break;
            case 2:{
                location.href='#/forFood';
            }break;
            case 3:{
            }break;
            case 4:{
                //领餐时间，点击领餐
                $scope.action='';
                $scope.status='已领取';
                //通知服务器用户已领取午餐
                //-------代码在这里-----------
                //-------代码在这里-----------
                //-------代码在这里-----------
            }break;
            case 5:{
            }break;
            case 6:{
            }break;
            default:break;
        }
    }
});