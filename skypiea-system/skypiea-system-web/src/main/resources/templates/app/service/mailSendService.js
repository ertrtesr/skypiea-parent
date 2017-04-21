(function (angular) {
    'use strict';

    angular.module('app').service('mailSendService', ['$http', 'baseUrl', function ($http, baseUrl) {

        this.sendMail = function (mail, fn) {
            console.log(mail);
            $http({
                url: baseUrl + '/sys/mail/send/simple',
                method: 'POST',
                data: mail
            }).then(function (resp) {
                fn(resp);
            });
        }

    }]);
})(angular)