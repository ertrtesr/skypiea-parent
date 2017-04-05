(function (angular) {
    'use strict';

    angular.module('app').service('userManagerService', ['$http', function ($http) {


        this.getUserCount = function (fn) {
            $http({
                url: '/sys/user/count',
                method: 'GET'
            }).then(function (resp) {
                fn(resp);
            })
        }

        this.getUserList = function (successFn) {
            $http({
                url: '/sys/user/all',
                method: 'GET'
            }).then(function (response) {
                successFn(response);
            });
        }


        /**
         * 按页码获取用户列表
         * @param currentPage
         * @param fn
         */
        this.getUserListByPage = function (currentPage, pageSize, fn) {
            $http({
                url: '/sys/user/all/' + currentPage,
                method: 'GET',
                params: {
                    "pageSize": pageSize
                }
            }).then(function (response) {
                fn(response);
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
                params: {
                    'id': userData.id,
                    'username': userData.username,
                    'password': userData.password,
                    'role.id': userData.role.id,
                    'role.name': userData.role.name
                }
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
                url: 'sys/token/' + token,
                method: 'GET',
            }).then(function successCallback(response) {
                fn(response);
            });
        }

    }]);
})(angular)