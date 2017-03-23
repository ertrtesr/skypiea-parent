(function (angular) {
    'use strict';

    angular.module('app').directive('appFoot', [function () {

        return {
            restrict: 'A',
            replace: true,
            templateUrl: 'script/view/template/foot.html'
        }
    }]);
})(angular);