/**
 * Created by Administrator on 2016/3/31.
 */
Lunchapp.controller('userCtrl',function ( $rootScope,$scope,url,tips) {
    $rootScope.nav='userCenter';

    hideSelectBox();
    $('#successTips').hide();

    var href = decodeURI(window.location);
    if(href.match('open_id=')){//这是一个带有微信openID的地址
        $scope.open_id=href.split('?').pop().split('&')[0].split('=').pop();
        sessionStorage.setItem('open_id',$scope.open_id);
    }else if(sessionStorage.getItem('open_id')){//从本地查找open_id
        $scope.open_id=sessionStorage.getItem('open_id');
    }

    $http({
        url:url.userInfo,
        method:'get',
        params:{
            open_id:$scope.open_id
        }
    }).success(function (data) {

    }).error(function () {
        $scope.warmTips=tips.serverClose;
        $('#warmTips').show();//显示温馨提示，服务器出现问题
    });

    $scope.changeSelection=function () {
        if( $('#selectBox').css('display') == 'none'){
            showSelectBox();
        }else{
            hideSelectBox();
        }
    }
});

//选择就餐地址
function selectAddress(event) {
    var address=event.target;
    address=$(address).text();
    console.log(address);
    $('.eatAddress').text(address);
    hideSelectBox();
}

//展开地址选择框
function showSelectBox() {
    $('#selectBox').show();
    $('#selectPic').removeClass('upsideDown');
}

//隐藏地址选择框
function hideSelectBox() {
    $('#selectBox').hide();
    $('#selectPic').addClass('upsideDown');
}

//修改资料
function changeData() {
    //修改失败

    //修改成功
    $('#successTips').show();
    clearTips('successTips',1000);
}