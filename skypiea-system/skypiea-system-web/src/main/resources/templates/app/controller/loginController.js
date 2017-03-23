(function (angular) {
    'use strict';

    angular.module('app').controller('loginController', [
        '$scope',
        'loginService',
        function ($scope, loginService) {


            $scope.userExist = true;            //判断用户是否存在的变量
            $scope.isPasswordRight = false;     //设置密码是否正确的变量

            $scope.userData = {
                username: '',
                password: '',
                confirmPassword: ''
            };

            //通过往service中传递回调函数,来获取service网络请求后得到的参数,否则获取不到
            $scope.checkUsername = function (username) {

                loginService.checkUsername(username, function (resp) {
                    // console.log(resp);
                    if (resp.data.data == null) {
                        //如果用户不存在
                        $scope.userExist = false;
                    } else {
                        $scope.userExist = true;
                    }
                });
            }

            //监听用户名变化,如果输入框中用户名改变的话,就不再显示"用户不存在的提示信息"
            $scope.$watch('userData.username', function (newVal, oldVal) {
                if (newVal != 'undefined') {
                    $scope.checkUsername(newVal);
                }
            });

            $scope.submitForm = function (userData) {
                loginService.checkPassword(userData, function (resp) {
                    console.log(resp);
                    if (resp.data.data == '密码正确') {
                        alert('密码正确');
                    } else {
                        alert('密码错误');
                    }
                });
            }

            //显示警告信息
            $scope.showAlert = function () {
                console.log('输入框中有错误信息,请重新填写');
            }

        }]);

})(angular)