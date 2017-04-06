(function (angular) {
    'use strict';

    angular.module('app')
        .directive('contentEditable', function () {
            return {
                restrict: 'A',
                require: 'ngModel',
                link: function (scope, element, attrs, ctrl) {
                    // 创建编辑器
                    var editor = new wangEditor('editor-trigger');
                    editor.onchange = function () {
                        // 从 onchange 函数中更新数据
                        scope.$apply(function () {
                            var html = editor.$txt.html();
                            ctrl.$setViewValue(html);
                        });
                    };
                    editor.config.uploadImgUrl = '/sys/mail/uploadImage';
                    editor.config.uploadImgFileName = 'image';
                    // editor.config.uploadParams = {
                    //     name: 'image'
                    // }
                    // // 设置 headers（举例）
                    // editor.config.uploadHeaders = {
                    //     'Content-Type': 'multipart/form-data'
                    // }
                    editor.create();
                }
            };
        })
})(angular)