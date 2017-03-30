(function (angular) {
    'use strict';
    angular.module("app").controller('homeController', [
        '$scope',
        '$rootScope',
        '$state',
        '$stateParams',
        'userManagerService',
        'localFactory',
        function ($scope, $rootScope, $state, $stateParams, userManagerService, localFactory) {

            //先从本地获取token
            var token = localFactory.get('accessToken');
            console.log('homeController启动了');
            //获取到登录的admin账号的信息
            userManagerService.getUserByToken(token, function (resp) {
                //获取服务器返回的对象
                var data = resp.data;
                //全局广播admin信息和token
                $rootScope.$broadcast("admin", data, token);
            });

        }]);
})(angular)


