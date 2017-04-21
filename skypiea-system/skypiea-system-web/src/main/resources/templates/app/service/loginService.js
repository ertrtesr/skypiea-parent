(function (angular) {
    'use strict';
    angular.module('app').service('loginService', ['$http', 'baseUrl', function ($http, baseUrl) {

        this.checkAdmin = function (username, successFn, errorFn) {
            $http.get(baseUrl + '/sys/checkAdmin?username=' + username).then(function (response) {
                successFn(response);            //将成功结果回调到loginController中
            }, function (error) {
                errorFn(error);
            })
        }

        //检查密码是否正确
        this.checkPassword = function (userData, successFn) {
            $http({
                url: baseUrl + '/sys/user/checkPwd',
                method: 'POST',
                params: {
                    username: userData.username,
                    password: userData.password
                }
            }).then(function (response) {
                successFn(response);
            });
        }

        this.login = function (username, password, fn) {
            $http({
                url: baseUrl + '/sys/login',
                method: 'POST',
                params: {
                    username: username,
                    password: password
                }
            }).then(function successCallback(response) {
                fn(response);
            });
        }

        this.logout = function (token, fn) {
            $http({
                url: baseUrl + '/sys/logout/' + token,
                method: 'GET'
            }).then(function (response) {
                fn(response);
            });
        }
    }]);

})(angular)