(function (angular) {

    'use strict';

    angular.module('app')       //必须这么写才能获取到app的module
        .directive('appMenu', [function () {
            return {
                restrict: 'A',
                replace: true,
                templateUrl: 'app/view/template/menu.html'
            }
        }])
})(angular)