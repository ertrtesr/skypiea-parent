(function (angular) {
    'use strict';

    angular.module('app').controller('mailController', ['$scope', function ($scope) {

        $scope.folds = [
            {name: 'Inbox', filter: ''},
            {name: 'Starred', filter: 'starred'},
            {name: 'Sent', filter: 'sent'},
            {name: 'Important', filter: 'important'},
            {name: 'Draft', filter: 'draft'},
            {name: 'Trash', filter: 'trash'}
        ];
    }]);
})(angular)