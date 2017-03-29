(function (angular) {
    'use strict';

    angular.module('app').service('loginService', ['$http', function ($http) {

        this.login = function (username, password, fn) {
            $http({
                url: '/client/user/loginByShiro',
                method: 'POST',
                params: {
                    username: username,
                    password: password
                }
            }).then(function (response) {
                fn(response);
            })
        }

        this.logout = function (accessToken, fn) {
            $http({
                url: '/client/user/logoutByShiro/' + accessToken,
                method: 'GET'
            }).then(function (response) {
                fn(response);
            });
        }

    }]);

})(angular)