myApp.controller('loginCtrl', ['$scope', '$http', 'postService', 'getService', 'SERVER',function($scope, $http, postService, getService, SERVER){

    if(window.sessionStorage.getItem("username") && window.sessionStorage.getItem("username") != ""){
        window.location.href = "#/manage";
    }
    $scope.login = function () {
        var user = {
            name: $scope.username,
            passwd: $scope.password
        };
        console.log($scope.username);
        if(($scope.username == "" && $scope.password == "") || $scope.username == undefined || $scope.password==undefined) {
            $scope.msg = "请输入用户名和密码";
        } else {
            $http({
                url: SERVER.commonUrl+"/PcUserController/getLoginUser",
                method: "POST",
                params: user
            }).success(function (data) {
                console.log(data);
                if (data.code == "2") {
                    //成功登陆
                    window.sessionStorage.setItem("username", user.name);
                    $scope.username = "";
                    $scope.password = "";
                    $scope.msg = "";
                    window.location.href = "#/manage";
                } else if (data.code == "1") {
                    //密码错误
                    $scope.msg = "密码输入错误";
                } else if (data.code == "0") {
                    //用户不存在
                    $scope.msg = "用户不存在";
                }
            }).error(function () {
                console.log("登陆失败");
            });
        }

        /*
        var promise = postService.query(SERVER.commonUrl+"/PcUserController/getLoginUser", user);
        promise.then(function (data) {
            //承诺用接口resolve()
            console.log(data);
            if (data.code == "2") {
                //成功登陆
                window.sessionStorage.setItem("username", user.name);
                $scope.username = "";
                $scope.password = "";
                $scope.msg = "";
                window.location.href = "#/manage";
            } else if (data.code == "1") {
                //密码错误
                $scope.msg = "密码输入错误";
            } else if (data.code == "0") {
                //用户不存在
                $scope.msg = "用户不存在";
            }
        }, function () {
            //承诺使用接口reject()
            console.log("获取失败");
        });

        */
        /*
        if(user.username == "defore") {
            if(user.password == "1") {
                window.sessionStorage.setItem("username", user.username);
                $scope.username = "";
                $scope.password = "";
                $scope.msg = "";
                window.location.href = "#/manage";
            } else {
                $scope.msg = "密码输入错误";
            }
        } else {
            $scope.msg = "用户不存在";
        }
        */
    };
    
    $scope.logout = function () {
        window.sessionStorage.removeItem("username");
        // $scope.isLogin = window.sessionStorage.getItem("username");
        window.location.href = "#/";
    };
}]);
