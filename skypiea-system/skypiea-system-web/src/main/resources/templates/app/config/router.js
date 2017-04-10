//路由控制器
'use strict';

angular.module('app').config([
    '$stateProvider',
    '$urlRouterProvider',
    '$locationProvider',
    function ($stateProvider, $urlRouterProvider, $locationProvider) {
        $urlRouterProvider.when("", "/user-manager");
        //用/mail/{fold}去匹配
        $urlRouterProvider.when('/mail', '/mail/{fold}');
        $stateProvider
            .state('login', {
                url: '/login',      //网址#后面所跟的路径
                templateUrl: 'app/view/login.html'      //对应显示的html页面
            })
            .state('home', {
                url: '',
                templateUrl: 'app/view/home.html',
                controller: 'homeController'
            })
            .state('home.user-manager', {  //这样的话user-manager.html就嵌套在home.html之下了,由home.html中的ui-view来管理
                url: '/user-manager',
                templateUrl: 'app/view/user-manager.html',
                controller: 'userManagerController'
            })
            .state('home.user-auth', {
                url: '/user-auth',
                templateUrl: 'app/view/user-auth.html'
            })
            .state('home.family-manager', {
                url: '/family-manager',
                templateUrl: 'app/view/family-manager.html'
            })
            .state('home.family-upload', {
                url: '/family-upload',
                templateUrl: 'app/view/family-upload.html'
            })
            .state('home.music-upload', {
                url: '/music-upload',
                templateUrl: 'app/view/music-upload.html'
            })
            .state('home.music-play', {
                url: '/music-play',
                templateUrl: 'app/view/music-play.html'
            })
            .state('home.photo-upload', {
                url: '/photo-upload',
                templateUrl: 'app/view/photo-upload.html',
            })
            .state('home.photo-beautify', {
                url: '/photo-beautify',
                templateUrl: 'app/view/photo-beautify.html'
            })
            .state('home.mail', {
                url: '/mail',
                templateUrl: 'app/view/mail.html'
            })
            .state('home.help', {
                url: '/help',
                templateUrl: 'app/view/help.html'
            })
            .state('home.about', {
                url: '/about',
                templateUrl: 'app/view/about.html'
            })
            .state('home.mail.send', {
                url: '/send',           //只需要加/send就行了,会自动拼接在/mail后面
                templateUrl: 'app/view/mail-send.html',
                controller: 'mailSendController'
            })
            .state('home.mail.list', {
                url: '/{fold}',
                templateUrl: 'app/view/mail-list.html'
            })
            .state('home.table-create', {
                url: '/table-create',
                templateUrl: 'app/view/table-create.html'
            })
            .state('home.table-parse', {
                url: '/table-parse',
                templateUrl: 'app/view/table-parse.html'
            });
        $urlRouterProvider.otherwise('/user-manager');
        $locationProvider.hashPrefix('');
    }]);

