myApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        // .when('/', {
        //     controller: 'indexCtrl',
        //     templateUrl: 'html/backstage/index.html'
        // })
        // .when('/post', {
        //     controller: 'postCtrl',
        //     templateUrl: 'html/backstage/test.html'
        // })
        // .when('/countList', {
        //     controller: 'countCtrl',
        //     templateUrl: 'html/backstage/countList.html'
        // })
        // .otherwise({
        //     redirectTo : '/'
        // });
        .when('/', {
            controller: "loginCtrl",
            templateUrl: "html/backstage/login.html"
        })
        .when('/manage', {
            controller: "manageCtrl",
            templateUrl: "html/backstage/manage.html"
        })
        .when('/count', {
            controller: "countCtrl",
            templateUrl: "html/backstage/count.html"
        })
        .when('/insert', {
            controller: "insertCtrl",
            templateUrl: "html/backstage/insert.html"
        })
        .otherwise({
            redirectTo: '/'
        })
}]);