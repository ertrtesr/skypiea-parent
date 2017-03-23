(function (angular) {
    'use strict';

    angular.module('app').directive('appMain', [function () {

        return {
            restrict: 'A',
            replace: true,
            templateUrl: 'script/view/template/main.html'
        }
    }]);
})(angular);