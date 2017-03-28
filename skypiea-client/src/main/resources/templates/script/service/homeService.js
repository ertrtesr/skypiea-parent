(function (angular) {
    'use strict';

    angular.module('app').service('homeService', ['$http', function ($http) {
        this.getUserByToken = function (accessToken, fn) {
            $http({
                url: '/client/user/token/' + accessToken,
                method: 'GET'
            }).then(function (resp) {
                fn(resp);
            });
        }
    }]);

})(angular)