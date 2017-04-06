(function (angular) {
    'use strict';

    angular.module('app').controller('mailSendController', [
        '$scope',
        'mailSendService', function ($scope, mailSendService) {

            // $scope.to = '';
            // $scope.subject = '';
            // $scope.editorContent = '';

            $scope.mail = {
                to: '',
                subject: '',
                content: ''
            }

            $scope.sendMail = function (mail) {
                mailSendService.sendMail(mail, function (resp) {
                    console.log(resp);
                });
            }

        }]);
})(angular)