(function (angular) {
    'use strict';

    angular.module('app').controller('mailController', ['$scope', function ($scope) {

        $scope.folds = [
            {name: "收件箱", filter: ''},
            {name: '星标邮件', filter: 'starred'},
            {name: '已发送', filter: 'sent'},
            {name: '重要邮件', filter: 'important'},
            {name: '草稿箱', filter: 'draft'},
            {name: '垃圾箱', filter: 'trash'}
        ];
    }]);
})(angular)