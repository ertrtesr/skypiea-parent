(function (angular) {
    'use strict';

    angular.module('app').controller('loginController', [
        '$scope',
        '$state',
        'loginService',
        'localFactory',
        function ($scope, $state, loginService, localFactory) {


            $scope.userExist = true;            //判断用户是否存在的变量
            $scope.isPasswordRight = false;     //设置密码是否正确的变量

            $scope.userData = {
                username: '',
                password: '',
                confirmPassword: ''
            };

            //通过往service中传递回调函数,来获取service网络请求后得到的参数,否则获取不到
            $scope.checkAdmin = function (username) {
                loginService.checkAdmin(username, function (resp) {
                    if (resp.data.status == 400) {
                        //用户名不存在
                        $scope.userExist = false;
                    } else {
                        $scope.userExist = true;
                    }
                });
            }

            //监听用户名变化,如果输入框中用户名改变的话,就不再显示"用户不存在的提示信息"
            $scope.$watch('userData.username', function (newVal, oldVal) {
                if (newVal != 'undefined') {
                    $scope.checkAdmin(newVal);
                }
            });

            $scope.login = function (username, password) {
                loginService.login(username, password, function (resp) {
                    if (resp.data.status == 200) {
                        //登录成功
                        var accessToken = resp.data.data;
                        //将token存储到本地
                        localFactory.set("accessToken", accessToken);
                        //跳转到首页
                        $state.go('home');
                    }
                });
            }

            //显示警告信息
            $scope.showAlert = function () {
                console.log('输入框中有错误信息,请重新填写');
            }

        }]);

})(angular)