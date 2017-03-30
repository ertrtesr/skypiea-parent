(function (angular) {
    'use strict';

    angular.module('app').service('userManagerService', ['$http', function ($http) {

        this.getUserList = function (successFn) {
            $http({
                url: '/sys/user/all',
                method: 'GET'
            }).then(function (response) {
                successFn(response);
            });
        }

        this.addUser = function (userData, fn) {
            $http({
                url: '/sys/user/addUser',
                method: 'POST',
                params: userData
            }).then(function (response) {
                fn(response);
            });
        }

        this.updateUser = function (userData, fn) {
            $http({
                url: '/sys/user/updateUser',
                method: 'POST',
                params: userData
            }).then(function (response) {
                fn(response)
            });
        };

        this.deleteUser = function (userData, fn) {
            $http({
                url: '/sys/user/deleteUser',
                method: 'GET',
                params: {
                    username: userData.username
                }
            }).then(function (response) {
                fn(response);
            });
        }

        this.getUserByToken = function (token, fn) {
            $http({
                url:'sys/token/'+token,
                method:'GET',
            }).then(function successCallback(response) {
                fn(response);
            });
        }

    }]);
})(angular)