(function (angular) {
    'use strict';

    angular.module('app').factory('localFactory', ['$window', function ($window) {

        return {
            set: function (key, value) {
                $window.localStorage[key] = value;
            },
            get: function (key, def) {
                return $window.localStorage[key] || def;
            },
            setObject: function (key, value) {
                $window.localStorage[key] = JSON.stringify(value);
            },
            getObject: function (key) {
                return JSON.parse($window.localStorage[key] || '{}');
            }
        }

    }]);

})(angular)