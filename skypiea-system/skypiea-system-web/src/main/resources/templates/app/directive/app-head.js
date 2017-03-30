(function (angular) {

    //启用语法严格模式
    'use strict';

    angular.module('app')
        .directive('appHead', [function () {
            return {
                restrict: 'A',
                replace: true,
                templateUrl: 'app/view/template/head.html',
                controller: 'headController'
            }
        }]);

})(angular)
