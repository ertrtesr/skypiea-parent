(function (angular) {

    'use strict';

    angular.module('app')
        .directive('appMain', [function () {
            return {
                restrict: 'A',
                replace: true,
                templateUrl: 'app/view/template/main.html'
            }
        }]);
})(angular)