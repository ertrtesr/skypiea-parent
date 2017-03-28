(function (angular) {

    'use strict';

    angular.module('app').service('registerService', ['$http', function ($http) {

        this.checkUsername = function (username, fn) {
            $http({
                //type为代表检查用户名,这是后台定义的规则
                url: '/client/user/check/' + username + "/1",
                method: 'GET'
            }).then(function (response) {
                fn(response);
            })
        }

        this.register = function (username, password, fn) {
            $http({
                url: '/client/user/register',
                method: 'POST',
                params: {
                    username: username,
                    password: password,
                    authorization: "普通用户"
                }
            }).then(function (response) {
                fn(response);
            })
        }


    }]);

})(angular)