(function (angular) {
    'use strict';

    angular.module('app').controller('registerController', ['$scope', '$state', '$stateParams', 'registerService',
        function ($scope, $state, $stateParams, registerService) {

            console.log('registerController');

            $scope.isAgree = false;
            $scope.usernameAvailable = true;

            $scope.userData = {
                username: '',
                password: '',
                confirmPassword: '',
            }

            // $scope.checkUsername = function (username) {
            //     registerService.checkUsername(username, function (resp) {
            //         console.log(resp);
            //     });
            // }

            $scope.$watch('userData.username', function (newVal, oldVal) {
                if (newVal != 'undefined' && newVal != null && newVal !== "") {
                    registerService.checkUsername(newVal, function (resp) {
                        //通过data是true还是false来进行判断
                        if (resp.data.data) {
                            //用户名未被注册,可以使用
                            $scope.usernameAvailable = true;
                        } else {
                            //用户名已被注册
                            $scope.usernameAvailable = false;
                        }
                    });
                }
            });

            $scope.register = function (userData) {
                var username = userData.username;
                var password = userData.password;

                registerService.register(username, password, function (resp) {
                    console.log(resp);
                    if (resp.data.status == 200) {
                        //注册成功,跳转到首页
                        $state.go('home.my');
                    }
                });
            };

            //显示警告信息
            $scope.showAlert = function () {
                alert('输入框中有错误信息,请重新填写');
            }
        }]);
})(angular)