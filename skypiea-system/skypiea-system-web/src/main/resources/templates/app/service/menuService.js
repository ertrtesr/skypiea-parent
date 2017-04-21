(function (angular) {
    'use strict';

    angular.module('app').service('menuService', ['$http', 'baseUrl', function ($http, baseUrl) {

        this.getMenuList = function (successFn) {
            console.log(baseUrl);

            $http.get(baseUrl + '/sys/menu/all').then(function (response) {
                successFn(response);
            });
        }
    }])
})(angular)