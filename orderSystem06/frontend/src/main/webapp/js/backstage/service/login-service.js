myApp.service('loginService', function () {
    this.isLogined = function () {
        var username = window.sessionStorage.getItem("username");
        if(username && username != "") {
            return true;
        } else {
            $('#msgBox').html("请先登录用户");
            $('#loginBox').modal('show');
            window.location.href="#/";
            return false;
        }
    }
});

