(function (angular) {
    'use strict';

    angular.module('app').controller('registerController', ['$scope', function ($scope) {

        console.log('registerController');

        $scope.isAgree = false;

        $scope.userData = {
            username: '',
            password: '',
            confirmPassword: '',
            email: ''
        }

        $scope.submitRegisterForm = function (userData) {

        };


    }]);
})(angular)