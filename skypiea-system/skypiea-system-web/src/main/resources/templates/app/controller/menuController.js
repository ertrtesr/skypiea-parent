(function (angular) {
    'use strict';
    angular.module('app').controller('menuController', [
        '$scope',
        '$state',
        '$location',
        'menuService', function ($scope, $state, $location, menuService) {

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

                /**
                 * 要考虑代码的加载顺序
                 */
                var srcPath = $location.path();
                var path = srcPath.substring(srcPath.indexOf("/") + 1);
                operateMenuByPath(path);        //根据路径操作菜单状态
                $scope.$apply();                //apply的作用就是让指定的表达式重新同步
            });

            /**
             * 根据路由#后面的路径来操作菜单的选中和开闭状态
             * @param path
             */
            var operateMenuByPath = function (path) {
                //定义一个menuId,用来跟menuList中的menuId进行比较
                var menuId = 11;
                //根据路由路径进行判断菜单的id
                if (path.startsWith('user-manager')) {
                    menuId = 11;
                } else if (path.startsWith('user-auth')) {
                    menuId = 12;
                } else if (path.startsWith('family-manager')) {
                    menuId = 21;
                } else if (path.startsWith('family-upload')) {
                    menuId = 22;
                } else if (path.startsWith('photo-upload')) {
                    menuId = 31;
                } else if (path.startsWith('photo-beautify')) {
                    menuId = 32;
                } else if (path.startsWith('music-upload')) {
                    menuId = 41;
                } else if (path.startsWith('music-play')) {
                    menuId = 42;
                } else if (path.startsWith('mail')) {
                    menuId = 5;
                }

                //此处只能用for循环,因为需要获得父子菜单的索引
                for (var i = 0; i < $scope.menuList.length; i++) {
                    //比较自定义的menuId和menuItem中的menuId是否相等
                    if (menuId == $scope.menuList[i].menuId) {
                        console.log('找到对应的:' + menuId);
                        console.log($scope.menuList[i]);
                        //父菜单:更改菜单的选中状态

                    } else {
                        for (var j = 0; j < $scope.menuList[i].childList.length; j++) {
                            if (menuId == $scope.menuList[i].childList[j].menuId) {
                                var sonMenuItem = $scope.menuList[i].childList[j];
                                console.log('找到对应的:' + menuId);
                                //找到子菜单以后:更改父菜单的开闭状态和选中状态,以及子菜单的选中状态
                                $scope.menuList[i].state = 'open';
                                console.log(sonMenuItem);
                                console.log($scope.menuList[i]);
                                break;
                            }
                        }
                    }
                }

            }




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
                        $state.go('home.family-upload');
                        break;
                    case constants.PHOTO_UPLOAD:
                        $state.go('home.photo-upload');
                        break;
                    case constants.PHOTO_BEAUTIFY:
                        $state.go('home.photo-beautify');
                        break;
                    case constants.MUSIC_UPLOAD:
                        $state.go('home.music-upload');
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

            /**
             * 该方法可以实时监听url的变化
             * 增加这段代码用于当用户认为修改地址栏进行跳转的情况
             * 侧边菜单的选中状态应该根据地址栏的路由参数进行改变才是正确的
             */
            $scope.$on("$locationChangeSuccess", function () {
                //监听url变化，在变化后做想要的处理
                path = $location.path();
                //获取到路由地址,即#号后面的那一部分去掉"/"
                path = path.substring(path.indexOf("/") + 1);
                operateMenuByPath(path);
            });


        }]);
})(angular)