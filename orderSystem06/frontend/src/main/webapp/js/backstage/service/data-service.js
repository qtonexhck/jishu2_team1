myApp.factory('getService', ['$http', '$q', 'SERVER', function ($http, $q, SERVER) {
    return {
        query: function (url, params) {
            var defer = $q.defer();//声明延时之后执行
            $http({
                method: "GET",
                url: url,
                params: params
            }).success(function (data) {
                defer.resolve(data);//声明执行成功
            }).error(function () {
                defer.reject();//声明执行失败
            });
            return defer.promise;//返回承诺,返回获取数据API
        }
    }
}]);

myApp.factory('postService', ['$http', '$q', function ($http, $q) {
    return {
        query: function (url, params) {
            var defer = $q.defer();//声明延时之后执行
            $http({
                method: "POST",
                url: url,
                params: params
            }).success(function (data) {
                defer.resolve(data);//声明执行成功
            }).error(function () {
                defer.reject();//声明执行失败
            });
            return defer.promise;//返回承诺,返回获取数据API
        }
    }
}]);

