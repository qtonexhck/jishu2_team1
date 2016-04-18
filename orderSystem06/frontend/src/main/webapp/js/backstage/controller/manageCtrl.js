myApp.controller('manageCtrl', ['$scope', '$rootScope', 'loginService', 'SERVER', '$http', function ($scope, $rootScope, loginService, SERVER, $http) {
    $rootScope.nav = "manage";
    loginService.isLogined();

    $scope.init = function () {
        $scope.date = new Date();
        $http({
            url: SERVER.commonUrl+"/DishRecordController/getTodayDish",
            type: "GET",
            params: {}
        }).success(function (data) {
            if(data.dishRecord.length >0){
                $scope.dateList = data.dishRecord;
            } else {
                $scope.dateList = [];
            }
        }).error(function () {
            console.log("订单加载失败");
        });
    };
    $scope.init();

    //打开弹框
    $scope.newItem = function (date) {
        var dateFormat = getDateFormat(date);
        $scope.date = new Date(dateFormat);//弹框的日期设置为要修改的日期
        $("#addBox").modal('show');
    };

    //保存
    $scope.saveItem = function (date) {
        var dateFormat = getDateFormat(date);
        console.log("已点击上传");
        var params = {
            date: dateFormat,
            inputA: $scope.valueA,
            inputB: $scope.valueB,
            inputC: $scope.valueC
        };
        $http({
            type: "POST",
            url: SERVER.commonUrl+"/DishRecordController/insertMenu",
            params: params
        }).success(function (data) {
            $scope.valueA = "";
            $scope.valueB = "";
            $scope.valueC = "";
            console.log(params);
            $("#addBox").modal('hide');
            $scope.init();
        }).error(function () {
            console.log("上交失败");
        });

    };

    //打开删除提示框
    $scope.openDelete = function (date) {
        var dateFormat = getDateFormat(date);
        $scope.deleteDate = dateFormat;
        $("#deleteMsgBox").modal('show');
    };
    //取消删除
    $scope.cancelDelete = function () {
        $("#deleteMsgBox").modal('hide');
    };

    //删除
    $scope.deleteItem = function (date) {
        var dateFormat = getDateFormat(date);
        var params = {
            date: dateFormat
        };
        $http({
            type: "POST",
            url: SERVER.commonUrl+"/DishRecordController/deleteMenu",
            params: params
        }).success(function (data) {
            console.log("删除date:"+dateFormat +"成功");
            $("#deleteMsgBox").modal('hide');
            $scope.init();
        }).error(function(){
            console.log("删除失败");
        });
    };

    //修改
    $scope.modItem = function (date) {
        var dateFormat = getDateFormat(date);
        var params = {
            date: dateFormat
        };
        $http({
            type: "POST",
            url: SERVER.commonUrl+"/DishRecordController/getTodayDish",
            params: params
        }).success(function(data){
            $scope.modDate = new Date(data.dishRecord[0].creTime);
            $scope.modValueA = data.dishRecord[0].dish.dishName;
            $scope.modValueB = data.dishRecord[1].dish.dishName;
            $scope.modValueC = data.dishRecord[2].dish.dishName;
            $("#modBox").modal('show');
            // console.log("修改date:"+dateFormat +"成功");
        }).error(function () {
            console.log("修改失败");
        })
    };

    $scope.updateItem = function (date) {
        var dateFormat = getDateFormat(date);
        var params = {
            date: dateFormat,
            inputA: $scope.modValueA,
            inputB: $scope.modValueB,
            inputC: $scope.modValueC
        };
        console.log(params);
        $http({
            type: "POST",
            url: SERVER.commonUrl+"/DishRecordController/updateMenu",
            params: params
        }).success(function(data){
            $scope.modValueA = "";
            $scope.modValueB = "";
            $scope.modValueC = "";
            $("#modBox").modal('hide');
            setTimeout(function () {
                $("#modMsgBox").modal('show');
            }, 500);
            setTimeout(function (){
                $("#modMsgBox").modal('hide');
            }, 3000);
            $scope.init();
            console.log("修改"+dateFormat+"成功");
        }).error(function () {
            console.log("修改失败");
        })
    };

    /*
    var data = new Array();


    //初始化
    $scope.init = function () {
        $scope.date = new Date();
        var dateValue = $scope.date.pattern("yyyy-MM-dd");
        // $scope.dateList = data;
        var arr = new Array();
        angular.forEach(data, function (data, index, array) {
            if(array[index].date >= dateValue) {
                arr.push(array[index]);
            }
        });
        $scope.dateList = arr;
    };
    $scope.init();

    //删除
    $scope.deleteItem = function (date) {
        //根据日期获取某日菜单,并删除
        var arr = data;
        angular.forEach(arr, function (data, index, array) {
            if(date == array[index].date) {
                array.splice(index, 1);
            }
        });
        $scope.init();
    };

    //修改
    $scope.modItem = function (date) {
        //根据日期获取某日菜单,修改菜单信息
        $scope.newItem(date);
    };

    //发布
    $scope.pushItem = function (date) {
        //根据日期获取某日菜单,修改发布状态
        //后期改成,修改数据库字段
        var arr = data;
        angular.forEach(arr, function (data, index, array) {
            if(date == array[index].date) {
                array[index].isPush = "1";
            }
        });
    };

    //input形式
    //新建
    $scope.newItem = function (date) {
        var dateFormat;//以字符串的形式获取date
        if(typeof date == "object") {
            dateFormat = date.pattern("yyyy-MM-dd");
        } else {
            dateFormat = date;
        }
        $scope.date = new Date(dateFormat);//弹框的日期设置为要修改的日期
        for(i in data) {
            if(dateFormat == data[i].date) {
                var item = {
                    id: data[i].id,
                    date: data[i].date,
                    modTime: data[i].modTime,
                    valueA: data[i].valueA,
                    valueB: data[i].valueB,
                    valueC: data[i].valueC,
                    isPush: data[i].isPush
                }
            }
        }
        if(item != null) {
            $scope.valueA = item.valueA;
            $scope.valueB = item.valueB;
            $scope.valueC = item.valueC;
            $scope.isPush = item.isPush;
        } else {
            $scope.valueA = "";
            $scope.valueB = "";
            $scope.valueC = "";
            $scope.isPush = "0";
        }

        $("#addBox").modal('show');

    };

    //修改完成提交到后台
    $scope.saveItem = function (date) {
        var dateFormat;//以字符串的形式获取date
        if(typeof date == "object") {
            dateFormat = date.pattern("yyyy-MM-dd");
        } else {
            dateFormat = date;
        }
        var valueA = $scope.valueA;
        var valueB = $scope.valueB;
        var valueC = $scope.valueC;
        //按日期排序
        function keyStr (key, desc) {
            //key->排序数组对象的键值, desc->是否要正序,true/false
            return function (a, b) {
                return desc ? (a[key]<b[key]) : (a[key]>b[key]);
            }
        }
        //flag:判断是否需要新增菜单. "0"->需要, "1"->已存在,且未发布, "2"->已发布,不可修改
        var flag = "0";
        angular.forEach(data, function (data, index, array) {
            //if(date === array[index].date) {
            if(dateFormat == array[index].date) {
                if(array[index].isPush == "0") {
                    console.log("更改了数据的日期:"+array[index].date);
                    array[index].valueA = valueA;
                    array[index].valueB = valueB;
                    array[index].valueC = valueC;
                    flag = "1";
                } else if (array[index].isPush == "1" ) {
                    flag = "2";
                }
            }
        });
        if(flag == "0") {
            var item = {
                id: 111,
                date: dateFormat,
                valueA: valueA,
                valueB: valueB,
                valueC: valueC,
                modTime: "9:00",
                isPush: "0"
            };
            data.push(item);
            data.sort(keyStr('date', false));
        }
        $scope.init();
        $("#addBox").modal('hide');
        console.log(data);
    };

    /*
    //select形式
    //新建
    $scope.newItem = function (date) {
        //获取菜式列表
        $scope.selectA = lunchList;
        $scope.selectB = lunchList;
        $scope.selectC = lunchList;
        var dateFormat;//以字符串的形式获取date
        if(typeof date == "object") {
            dateFormat = date.pattern("yyyy-MM-dd");
        } else {
            dateFormat = date;
        }
        $scope.date = new Date(dateFormat);//弹框的日期设置为要修改的日期
        //发送日期过去,获取当日详情
        //比如获得
        for(i in data) {
            if(dateFormat == data[i].date) {
                var item = {
                    id: data[i].id,
                    date: data[i].date,
                    modTime: data[i].modTime,
                    selectKeyA: data[i].selectKeyA,
                    selectKeyB: data[i].selectKeyB,
                    selectKeyC: data[i].selectKeyC
                }
            }
        }
        if(item != null) {
            $scope.selectKeyA = item.selectKeyA;
            $scope.selectKeyB = item.selectKeyB;
            $scope.selectKeyC = item.selectKeyC;
        } else {
            $scope.selectKeyA = lunchList[0].key;
            $scope.selectKeyB = lunchList[0].key;
            $scope.selectKeyC = lunchList[0].key;
        }

        $("#addBox").modal('show');
    };


    //修改完成提交到后台
    $scope.saveItem = function (date) {
        // var date = $scope.date.pattern("yyyy-MM-dd");
        var dateFormat;//以字符串的形式获取date
        if(typeof date == "object") {
            dateFormat = date.pattern("yyyy-MM-dd");
        } else {
            dateFormat = date;
        }
        var selectKeyA = $scope.selectKeyA;
        var selectKeyB = $scope.selectKeyB;
        var selectKeyC = $scope.selectKeyC;

        //按日期排序
        function keyStr (key, desc) {
            //key->排序数组对象的键值, desc->是否要正序,true/false
            return function (a, b) {
                return desc ? (a[key]<b[key]) : (a[key]>b[key]);
            }
        }
        var flag = "0";
        angular.forEach(data, function (data, index, array) {
            //if(date === array[index].date) {
            if(dateFormat == array[index].date) {
                if(array[index].isPush == "0") {
                    console.log("更改了数据的日期:"+array[index].date);
                    array[index].selectKeyA = selectKeyA;
                    array[index].selectKeyB = selectKeyB;
                    array[index].selectKeyC = selectKeyC;
                    flag = "1";
                } else if (array[index].isPush == "1" ) {
                    flag = "2";
                }
            }
        });
        if(flag == "0") {
            var item = {
                id: 111,
                date: dateFormat,
                selectKeyA: selectKeyA,
                selectKeyB: selectKeyB,
                selectKeyC: selectKeyC,
                modTime: "9:00",
                isPush: "0"
            };
            data.push(item);
            data.sort(keyStr('date', false));
        }
        $scope.init();
        $("#addBox").modal('hide');
        console.log(data);
    };
    */

}]);

/*
//定义一个过滤器,通过value找到key;
myApp.filter('getSelectValue', function() {
    //初始化套餐列表
    var lunchList = [
        {
            value: "白灼虾+汤爆双脆",
            key: "1"
        }, {
            value: "海鲜卤面+大虾烧白菜",
            key: "2"
        }, {
            value: "萝卜牛腩+青瓜炒肉片",
            key: "3"
        }, {
            value: "酱猪蹄+辣炒蛤蜊",
            key: "4"
        }, {
            value: "一品豆腐+回锅肉",
            key: "5"
        }, {
            value: "家常烧牙片鱼+菊花虾包",
            key: "6"
        }, {
            value: "麻婆豆腐+馄饨",
            key: "7"
        }
    ];


    return function (key) {
        var arr = lunchList;
        var value = "";
        for(i in arr) {
            if(key == arr[i].key) {
                value = arr[i].value;
            }
        }
        return value;
    }
});
*/