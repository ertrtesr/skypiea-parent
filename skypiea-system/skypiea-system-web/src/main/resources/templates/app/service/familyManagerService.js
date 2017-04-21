(function (angular) {
    'use strict';

    angular.module('app').service('familyService', [
        '$http',
        function ($http) {

            this.getAllFamilies = function (fn) {
                $http({
                    url: '/sys/family/all',
                    method: 'GET'
                }).then(function (resp) {
                    fn(resp.data);
                });
            }
        }]);
})(angular)