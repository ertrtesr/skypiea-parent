(function (angular) {
    'use strict';

    angular.module('app').controller('homeController', [
        '$scope',
        '$rootScope',
        'homeService',
        'localFactory',
        function ($scope, $rootScope, homeService, localFactory) {
            console.log('homeController');
            var accessToken = localFactory.get('accessToken');
            //每次刷新主页都会进到这个控制器,在这里需要通过accessToken请求服务器获取用户数据,展示在页面上
            if (accessToken != null) {
                homeService.getUserByToken(accessToken, function (resp) {
                    console.log(resp);
                    var userInfo = resp.data.data;
                    $rootScope.$broadcast('currentUser', userInfo);
                });
            }

        }]);
})(angular)