myApp.controller('insertCtrl', ['$scope', '$rootScope', 'loginService', 'SERVER', function ($scope, $rootScope, loginService, SERVER) {
    $rootScope.nav = "insert";
    loginService.isLogined();
    var data = [
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
    $scope.init = function () {
        $scope.menu = "";
        $scope.list = data;
    };
    $scope.init();
    //新增菜式
    $scope.addItem = function () {
        $("#addBox").modal('show')
    };
    //保存菜式
    $scope.saveOne = function (menu) {
        var arr = data;
        var size = arr.length;
        var id = (size+1);
        var item = {
            key: id,
            value: menu
        };
        arr.push(item);
        $scope.init();
        $("#addBox").modal('hide');
    };
    //删除菜式
    $scope.deleteItem = function (key) {
        //根据日期获取某日菜单,并删除
        var arr = data;
        angular.forEach(arr, function (data, index, array) {
            if(key == array[index].key) {
                array.splice(index, 1);
            }
        });
        $scope.init();
    }
}]);