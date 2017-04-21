(function (angular) {
    'use strict';

    angular.module('app').controller('familyController', [
        '$scope',
        'familyService',
        function ($scope, familyService) {

            $scope.families = [];

            var getAllFamilies = $scope.getAllFamilies = function () {
                familyService.getAllFamilies(function (data) {
                    console.log(data);
                    if (data.msg == "ok") {
                        $scope.families = data.data;
                    }
                });
            }

            getAllFamilies();

        }]);
})(angular)