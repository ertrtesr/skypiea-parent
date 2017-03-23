(function (angular) {
    'use strict';

    angular.module('app').config([
        '$stateProvider',
        '$urlRouterProvider',
        '$locationProvider', function ($stateProvider, $urlRouterProvider, $locationProvider) {
            $urlRouterProvider.when("", "/my");
            $stateProvider
                .state('register', {
                    url: '/register',
                    templateUrl: 'script/view/register.html',
                    controller: 'registerController'
                })
                .state('login', {
                    url: '/login',
                    templateUrl: 'script/view/login.html',
                    controller: 'loginController'
                })
                .state('home', {
                    url: '',
                    templateUrl: 'script/view/home.html',
                    controller: 'homeController'
                })
                .state('home.my', {
                    url: '/my',
                    templateUrl: 'script/view/my.html'
                })
                .state('home.family',{
                    url:'/family',
                    templateUrl:'script/view/family.html'
                })
                .state('home.photo',{
                    url:'/photo',
                    templateUrl:'script/view/photo.html'
                })
                .state('home.music',{
                    url:'/music',
                    templateUrl:'script/view/music.html'
                });

            $urlRouterProvider.otherwise('/home');
            $locationProvider.hashPrefix('');
        }]);
})(angular)