(function (angular) {
    'use strict';
    angular.module('app').controller('headController', ['$scope', function ($scope) {

        console.log('headController启动了');
        $scope.showUsername = false;
        $scope.showLoginButton = true;
        $scope.username = '';

        $scope.$on('responseData', function (event, data) {
            console.log(data);
            if (data.status == 200) {
                $scope.username = data.data.username;
                $scope.showUsername = true;             //显示用户名
                $scope.showLoginButton = false;         //隐藏登录按钮
            } else {
                //用户token不存在的情况
                $scope.showUsername = false;
                $scope.showLoginButton = true;
            }
        });

    }]);
})(angular)