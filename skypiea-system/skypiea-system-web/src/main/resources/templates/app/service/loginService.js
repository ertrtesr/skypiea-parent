(function (angular) {
    'use strict';
    angular.module('app').service('loginService', ['$http', function ($http) {

        this.checkUsername = function (username, successFn, errorFn) {

            $http.get('/sys/user?username=' + username).then(function (response) {
                successFn(response);            //将成功结果回调到loginController中
            }, function (error) {
                errorFn(error);
            })
        }

        //检查密码是否正确
        this.checkPassword = function (userData, successFn) {
            $http({
                url: '/sys/user/checkPwd',
                method: 'POST',
                params: {
                    username: userData.username,
                    password: userData.password
                }
            }).then(function (response) {
                successFn(response);
            });
        }
    }]);

})(angular)