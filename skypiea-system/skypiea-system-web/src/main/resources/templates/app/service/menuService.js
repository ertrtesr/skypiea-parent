(function (angular) {
    'use strict';

    angular.module('app').service('menuService', ['$http', function ($http) {

        this.getMenuList = function (successFn, errorFn) {

            $http.get('/sys/menu/all').then(function (response) {
                successFn(response);
            }, function (error) {
                errorFn(error);
            });
        }
    }])
})(angular)