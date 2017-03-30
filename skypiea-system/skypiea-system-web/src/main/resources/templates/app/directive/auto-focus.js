(function (angular) {
    'use strict';

    angular.module('app').directive('autoFocus', ['$location', function ($location) {

        return {
            restrict: 'A',
            link: function ($scope, iElm, iAttrs, controller) {
                iElm.on('click', function () {
                    // iElm.addClass('a-menu-active');
                })
            }
        };

    }]);
})(angular)