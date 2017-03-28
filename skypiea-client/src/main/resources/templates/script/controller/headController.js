(function (angular) {
    'use strict';
    angular.module('app').controller('headController', ['$scope', function ($scope) {

        console.log('headController启动了');
        $scope.username = '登录'

        $scope.$on('currentUser', function (event, userInfo) {
            console.log(userInfo);
            $scope.username = userInfo.username;
        });

    }]);
})(angular)