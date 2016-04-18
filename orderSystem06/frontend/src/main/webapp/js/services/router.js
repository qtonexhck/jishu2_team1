/**
 * Created by Administrator on 2016/3/29.
 */

// 路由配置

Lunchapp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'forFoodCtrl',
            templateUrl: 'html/forFood.html'
        })
        .when('/forFood', {
            controller: 'forFoodCtrl',
            templateUrl: 'html/forFood.html'
        })
        .when('/user', {
            controller: 'userCtrl',
            templateUrl: 'html/user.html'
        })
        .when('/changePhone', {
            controller: 'changePhoneCtrl',
            templateUrl: 'html/changePhone.html'
        })
        .when('/payStatus', {
            controller: 'payStatusCtrl',
            templateUrl: 'html/payStatus.html'
        })
        .when('/myFood', {
            controller: 'myFoodCtrl',
            templateUrl: 'html/myFood.html'
        })

        
        
        
        
        
        
        
        .otherwise({
            redirectTo : '/'
        });
}]);