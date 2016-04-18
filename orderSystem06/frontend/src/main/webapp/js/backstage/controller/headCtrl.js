myApp.controller('headCtrl', function ($scope, $rootScope) {
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
})