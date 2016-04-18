myApp.controller('countCtrl', ['$scope', '$rootScope', 'loginService', 'postService' ,'$http' , 'SERVER', function ($scope, $rootScope, loginService, postService, $http, SERVER) {
    $rootScope.nav = "count";
    loginService.isLogined();

    //初始化
    //$scope.date = new Date();
    $scope.date = new Date("2016-04-06");
    $scope.type = "0";

    var places = ["17楼", "18楼", "创客"];
    
    $scope.searchNew = function (type) {
        // type "0"->订单详情  "1"->用户详情  "2"->未领取详情
        var typeValue = (type == 'undefined')? "0": type;
        // console.log(typeValue);

        $scope.type = typeValue;

        var date = $scope.date.pattern("yyyy-MM-dd");
        // var date = "2016-04-06";
        var params = {
            consumptionDate: date  
        };
        if($scope.type == "0") {
            $http({
                url: SERVER.commonUrl+"/OrderStatisticsController/getOrderInfo01",
                type: "GET",
                params: params
            }).success(function (data) {
                console.log(data);
                var item1 = {
                    location: places[0],
                    aCount: 0,
                    bCount: 0,
                    cCount: 0
                };
                var item2 = {
                    location: places[1],
                    aCount: 0,
                    bCount: 0,
                    cCount: 0
                };
                var item3 = {
                    location: places[2],
                    aCount: 0,
                    bCount: 0,
                    cCount: 0
                };
                //17
                if(data.floor17.length == 0) {
                    item1 = item1;
                } else if(data.floor17.length == 3) {
                    item1.aCount = data.floor17[0].TheCount;
                    item1.bCount = data.floor17[1].TheCount;
                    item1.cCount = data.floor17[2].TheCount;
                } else if(data.floor17.length == 2) {
                    if(data.floor17[0].TheCount==1 && data.floor17[1].TheCount==2) {
                        item1.aCount = data.floor17[0].TheCount;
                        item1.bCount = data.floor17[1].TheCount;
                    } else if(data.floor17[0].TheCount==1 && data.floor17[1].TheCount==3) {
                        item1.aCount = data.floor17[0].TheCount;
                        item1.cCount = data.floor17[1].TheCount;
                    } else if(data.floor17[0].TheCount==2 && data.floor17[1].TheCount==3) {
                        item1.bCount = data.floor17[0].TheCount;
                        item1.cCount = data.floor17[1].TheCount;
                    }
                } else if (data.floor17.length == 1) {
                    if(data.floor17[0].TheType == 1) {
                        item1.aCount = data.floor17[0].TheCount;
                    } else if(data.floor17[0].TheType == 2) {
                        item1.bCount = data.floor17[0].TheCount;
                    } else if(data.floor17[0].TheType == 3) {
                        item1.cCount = data.floor17[0].TheCount;
                    }
                }
                //18
                if(data.floor18.length == 0) {
                    item2 = item2;
                } else if(data.floor18.length == 3) {
                    item2.aCount = data.floor18[0].TheCount;
                    item2.bCount = data.floor18[1].TheCount;
                    item2.cCount = data.floor18[2].TheCount;
                } else if(data.floor18.length == 2) {
                    if(data.floor18[0].TheCount==1 && data.floor18[1].TheCount==2) {
                        item2.aCount = data.floor18[0].TheCount;
                        item2.bCount = data.floor18[1].TheCount;
                    } else if(data.floor18[0].TheCount==1 && data.floor18[1].TheCount==3) {
                        item2.aCount = data.floor18[0].TheCount;
                        item2.cCount = data.floor18[1].TheCount;
                    } else if(data.floor18[0].TheCount==2 && data.floor18[1].TheCount==3) {
                        item2.bCount = data.floor18[0].TheCount;
                        item2.cCount = data.floor18[1].TheCount;
                    }
                } else if (data.floor18.length == 1) {
                    if(data.floor18[0].TheType == 1) {
                        item2.aCount = data.floor18[0].TheCount;
                    } else if(data.floor18[0].TheType == 2) {
                        item2.bCount = data.floor18[0].TheCount;
                    } else if(data.floor18[0].TheType == 3) {
                        item2.cCount = data.floor18[0].TheCount;
                    }
                }
                //15
                if(data.floor05.length == 0) {
                    item3 = item3;
                } else if(data.floor05.length == 3) {
                    item3.aCount = data.floor05[0].TheCount;
                    item3.bCount = data.floor05[1].TheCount;
                    item3.cCount = data.floor05[2].TheCount;
                } else if(data.floor05.length == 2) {
                    if(data.floor05[0].TheCount==1 && data.floor05[1].TheCount==2) {
                        item3.aCount = data.floor05[0].TheCount;
                        item1.bCount = data.floor05[1].TheCount;
                    } else if(data.floor05[0].TheCount==1 && data.floor05[1].TheCount==3) {
                        item3.aCount = data.floor05[0].TheCount;
                        item3.cCount = data.floor05[1].TheCount;
                    } else if(data.floor05[0].TheCount==2 && data.floor05[1].TheCount==3) {
                        item3.bCount = data.floor05[0].TheCount;
                        item3.cCount = data.floor05[1].TheCount;
                    }
                } else if (data.floor05.length == 1) {
                    if(data.floor05[0].TheType == 1) {
                        item3.aCount = data.floor05[0].TheCount;
                    } else if(data.floor05[0].TheType == 2) {
                        item3.bCount = data.floor05[0].TheCount;
                    } else if(data.floor05[0].TheType == 3) {
                        item3.cCount = data.floor05[0].TheCount;
                    }
                }
                $scope.item17 = item1;
                $scope.item18 = item2;
                $scope.item19 = item3;
            }).error(function () {
                console.log("获取数据失败");
            });
        } else if($scope.type == "1") {
            var params = {
                time: date
            };
            $http({
                url: SERVER.commonUrl+"/OrderStatisticsController/getMealInfo",
                type: "GET",
                params: params
            }).success(function (data) {
                $scope.list = data.getMealInfo;
            }).error(function () {
                console.log("获取领取详情失败");
            });
        }
    };

    $scope.searchNew("0");


    /*
    //模拟数据
    //订单详情

    //用户详情

    //初始化
    $scope.date = new Date();
    $scope.type = "0";

    $scope.search = function (type) {
        // type "0"->订单详情  "1"->用户详情  "2"->未领取详情
        var typeValue = (type == 'undefined')? type: type;
        // console.log(typeValue);

        $scope.type = typeValue;

        var date = $scope.date.pattern("yyyy-MM-dd");

        if($scope.type == "0") {
            //0.订单详情
            //日期筛选
            var arr = new Array();
            angular.forEach(data, function (data, index, array) {
                if(array[index].date == date) {
                    arr.push(array[index]);
                }
            });
            $scope.list = arr;
            //统计
            var aNum = 0, bNum = 0, cNum = 0;
            for(i in arr) {
                aNum += arr[i].aCount;
                bNum += arr[i].bCount;
                cNum += arr[i].cCount;
            }
            $scope.aCountNum = aNum;
            $scope.bCountNum = bNum;
            $scope.cCountNum = cNum;

        } else if($scope.type == "1") {
            //1.用户详情
            var arr = new Array();
            angular.forEach(user, function (data, index, array) {
                if(array[index].date == date) {
                    arr.push(array[index]);
                }
            });
            $scope.list = arr;
        }
        // else if($scope.type == "2") {
        //     //2.未领取详情
        //     var arr = new Array();
        //     angular.forEach(user, function (data, index, array) {
        //         if(array[index].date == date && array[index].type == $scope.type) {
        //             arr.push(array[index]);
        //         }
        //     });
        //     $scope.list = arr;
        // }

    };
    $scope.search(0);
    */
}]);


//领取状态过滤器
myApp.filter('getValueByStatus', function() {
    //定义状态
    var statusList = [
        {
            value: "未领取",
            key: "1"
        }, {
            value: "已领取",
            key: "2"
        }
    ];
    return function (key) {
        var arr = statusList;
        var value = "";
        for(i in arr) {
            if(key == arr[i].key) {
                value = arr[i].value;
            }
        }
        return value;
    }
});

myApp.filter('getValueType', function () {
    //定义选取类型
    var statusList = [
        {
            value: "A餐",
            key: "1"
        }, {
            value: "B餐",
            key: "2"
        }, {
            value: "C餐",
            key: "3"
        }
    ];
    return function (key) {
        var arr = statusList;
        var value = "";
        for(i in arr) {
            if(key == arr[i].key) {
                value = arr[i].value;
            }
        }
        return value;
    }
});

params = {
    date: "2016-04-06",
    inputA: "萝卜牛腩+青瓜炒肉片",
    inputB: "一品豆腐+回锅肉",
    inputC: "家常烧牙片鱼+菊花虾包"
}