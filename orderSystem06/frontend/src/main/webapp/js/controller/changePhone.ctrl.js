Lunchapp.controller('changePhoneCtrl',function ($rootScope) {
    $rootScope.nav='userCenter';
    $('title').html('修改手机号码');
    $('#successTips').hide();

    var href = decodeURI(window.location);
    if(href.match('open_id=')){//这是一个带有微信openID的地址
        $scope.open_id=href.split('?').pop().split('&')[0].split('=').pop();
        sessionStorage.setItem('open_id',$scope.open_id);
    }else if(sessionStorage.getItem('open_id')){//从本地查找open_id
        $scope.open_id=sessionStorage.getItem('open_id');
    }
});

function changePhone() {
    $('#successTips').show();
    clearTips('successTips',1000);
}

//检查输入的手机号是否合法
function checkNumber(input) {
    var str=input.value;
    var test=/^1\d{0,10}$/;
    if(!test.test(str)){
        input.value=null;
    }
}

//检查输入的验证码是否合法
function checkCode(input) {
    var str=input.value;
    var test=/^\d{0,6}$/;
    if(!test.test(str)){
        input.value=null;
    }
}

