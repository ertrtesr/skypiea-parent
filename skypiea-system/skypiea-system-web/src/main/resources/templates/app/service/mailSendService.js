(function (angular) {
    'use strict';

    angular.module('app').service('mailSendService', ['$http', function ($http) {

        this.sendMail = function (mail, fn) {
            console.log(mail);
            $http({
                url: '/sys/mail/send/simple',
                method: 'POST',
                data: mail
            }).then(function (resp) {
                fn(resp);
            });
        }

    }]);
})(angular)