myApp.directive('headerNav', function () {
    return {
        restrict: 'E',
        templateUrl: 'html/backstage/tpl/head.html',
        scope: {
            username: "@",
            password: "@",
            msg: "@",
            isLogin: "@"
        },
        controller: function ($scope) {
            $scope.isLogin = window.sessionStorage.getItem("username");
            //登录
            $scope.login = function () {
                console.log("已登录");
            };
            //登出
            $scope.logout = function () {
                window.sessionStorage.removeItem("username");
                $scope.isLogin = window.sessionStorage.getItem("username");
                window.location.href = "#/";
            }
        }
    }
});