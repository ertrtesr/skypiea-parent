(function (angular) {
    'use strict';
    angular.module('app').controller('menuController', ['$scope', '$state', '$stateParams', 'menuService', function ($scope, $state, $stateParams, menuService) {


        console.log($stateParams);


        var constants = {
            USER_MANAGER: 11,
            USER_AUTH: 12,
            FAMILY_MANAGER: 21,
            FAMILY_UPLOAD: 22,
            PHOTO_UPLOAD: 31,
            PHOTO_BEAUTIFY: 32,
            MUSIC_UPLOAD: 41,
            MUSIC_PLAY: 42,
            MAIL: 5
        }

        $scope.menuList = [];
        $scope.showSon = false;
        $scope.showGrandSon = false;
        $scope.clickedItem = {};

        menuService.getMenuList(function (resp) {
            $scope.menuList = resp.data;
        });


        //切换列表状态
        $scope.toggleState = function (index) {
            var state = $scope.menuList[index].state;
            if (state == 'closed') {
                state = 'open';
            } else {
                state = 'closed';
            }
            console.log(index);
            console.log('父状态:' + state);
            $scope.menuList[index].state = state;
        }

        $scope.toggleSonState = function (outerIndex, innerIndex) {
            var state = $scope.menuList[outerIndex].childList[innerIndex].state;
            console.log('外索引:' + outerIndex + ";内索引:" + innerIndex);
            console.log('子状态' + state);

            if (state == 'closed') {
                state = 'open';
            } else {
                state = 'closed';
            }
            $scope.menuList[outerIndex].childList[innerIndex].state = state;
        }

        //通过路由跳转页面
        $scope.goPage = function (menuId) {
            console.log(menuId);
            switch (menuId) {
                case constants.USER_MANAGER:
                    $state.go('home.user-manager');
                    break;
                case constants.USER_AUTH:
                    $state.go('home.user-auth');
                    break;
                case constants.FAMILY_MANAGER:
                    $state.go('home.family-manager');
                    break
                case constants.FAMILY_UPLOAD:
                    $state.go('home.family-file');
                    break;
                case constants.PHOTO_UPLOAD:
                    $state.go('home.photo-file');
                    break;
                case constants.PHOTO_BEAUTIFY:
                    $state.go('home.photo-beautify');
                    break;
                case constants.MUSIC_UPLOAD:
                    $state.go('home.music-file');
                    break;
                case constants.MUSIC_PLAY:
                    $state.go('home.music-play');
                    break;
                case constants.MAIL:
                    $state.go('home.mail');
                    break;
                default:
                    break;
            }
        }

    }]);
})(angular)