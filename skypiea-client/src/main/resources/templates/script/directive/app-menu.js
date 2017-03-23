(function (angular) {
    'use strict';

    angular.module('app').directive('appMenu', [function () {

        return {
            restrict: 'A',
            replace: true,
            templateUrl: 'script/view/template/menu.html'
        }
    }]);
})(angular);