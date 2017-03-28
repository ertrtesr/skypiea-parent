(function (angular) {
    'use strict';

    angular.module('app').directive('appHead', [function () {

        return {
            restrict: 'A',
            replace: true,
            templateUrl: 'script/view/template/head.html',
            controller: 'headController'
        }

    }]);
})(angular)