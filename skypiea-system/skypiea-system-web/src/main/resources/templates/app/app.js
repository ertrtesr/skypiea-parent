(function (angular) {
    'use strict';
    var app = angular.module('app', ['ui.router', 'ui.bootstrap', 'angularFileUpload']);

    // 定义主机地址
    // app.constant('baseUrl', 'http://139.196.124.44:8080/mm');

    app.constant('baseUrl', 'http://localhost:8080');
})(angular)

