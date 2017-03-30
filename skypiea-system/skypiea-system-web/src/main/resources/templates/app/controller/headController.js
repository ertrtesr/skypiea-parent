(function (angular) {

    'use strict';
    angular.module('app').controller('headController', ['$scope', '$state', 'loginService', function ($scope, $state, loginService) {

        $scope.showLoginButton = true;
        $scope.showAdmin = false;

        $scope.username = '';
        $scope.token = '';

        $scope.$on('admin', function (event, data, token) {
            console.log(data);
            console.log(token);
            var admin = data.data;
            $scope.token = token;

            if (data.status == 200) {
                $scope.showAdmin = true;
                $scope.showLoginButton = false;
                $scope.username = admin.username;
            } else {
                $scope.showAdmin = false;
                $scope.showLoginButton = true;
            }
        });

        $scope.logout = function (token) {
            loginService.logout(token, function (resp) {
                if (resp.data.status == 200) {
                    //退出成功,跳转到登录页面
                    $state.go('login');
                }
            })
        }

    }]);

})(angular)