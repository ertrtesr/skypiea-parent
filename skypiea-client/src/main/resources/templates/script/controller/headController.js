(function (angular) {
    'use strict';
    angular.module('app').controller('headController', [
        '$scope',
        '$state',
        'loginService',
        function ($scope, $state, loginService) {

            console.log('headController启动了');
            $scope.showUsername = false;
            $scope.showLoginButton = true;
            $scope.showPopMenu = false;
            $scope.username = '';
            $scope.accessToken = '';        //用户token

            $scope.logout = function (accessToken) {
                loginService.logout(accessToken, function (resp) {
                    if (resp.data.status == 200) {
                        //注销成功,跳转到登录页面
                        $state.go('login');
                    }
                });
            }


            //接收从homeController广播过来的参数
            $scope.$on('responseData', function (event, data, accessToken) {
                console.log(data);
                console.log(accessToken);
                if (data.status == 200) {
                    $scope.username = data.data.username;
                    $scope.accessToken = accessToken;

                    $scope.showUsername = true;             //显示用户名
                    $scope.showLoginButton = false;         //隐藏登录按钮
                    $scope.showPopMenu = true;              //显示下拉菜单和向下的小箭头
                } else {
                    //用户token不存在的情况
                    $scope.showUsername = false;
                    $scope.showLoginButton = true;
                    $scope.showPopMenu = false;
                }
            });

        }]);
})(angular)