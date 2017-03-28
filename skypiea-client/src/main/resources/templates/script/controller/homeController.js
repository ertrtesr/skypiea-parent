(function (angular) {
    'use strict';

    angular.module('app').controller('homeController', ['$scope', 'homeService', 'localFactory', function ($scope, homeService, localFactory) {
        console.log('homeController');
        var accessToken = localFactory.get('accessToken');
        console.log(accessToken);
        //每次刷新主页都会进到这个控制器,在这里需要通过accessToken请求服务器获取用户数据,展示在页面上
        // $scope.getUserByToken = function (accessToken) {
        //     homeService.getUserByToken(accessToken, function (resp) {
        //         console.log('根据token:' + accessToken + '--获取到的用户数据是:' + resp);
        //     });
        // }
        if (accessToken != null) {
            homeService.getUserByToken(accessToken, function (resp) {
                console.log(resp);
            });
        }

    }]);
})(angular)