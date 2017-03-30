(function (angular) {
    'use strict';

    angular.module('app').controller('loginController', [
        '$scope',
        '$state',
        '$stateParams',
        'loginService',
        'localFactory',     //本地存储
        function ($scope, $state, $stateParams, loginService, localFactory) {

            $scope.userData = {
                username: '',
                password: '',
            };

            $scope.login = function (username, password) {
                loginService.login(username, password, function (resp) {
                    if (resp.data.status == 200) {
                        //登录成功
                        //获取到服务器返回的token
                        var accessToken = resp.data.data;
                        //将token存到本地
                        localFactory.set('accessToken', accessToken);
                        // 跳转到首页
                        $state.go('home');
                    } else {
                        alert("登录信息有误!");
                    }
                });
            }

            $scope.showAlert = function () {
                alert("输入框中信息有误,请重新填写");
            }
        }]);

})(angular)