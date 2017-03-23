(function (angular) {
    'use strict';
    angular.module("app").controller('homeController', ['$scope', '$state', '$stateParams', function ($scope, $state, $stateParams) {
        console.log($state.href());
    }]);
})(angular)


