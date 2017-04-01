(function (angular) {
    'use strict';

    angular.module('app')
        .directive('contentEditable', function () {
            return {
                restrict: 'A' ,
                require: 'ngModel',
                link: function(scope, element, attrs, ctrl) {
                    // 创建编辑器
                    var editor = new wangEditor('editor-trigger');
                    editor.onchange = function () {
                        // 从 onchange 函数中更新数据
                        scope.$apply(function () {
                            var html = editor.$txt.html();
                            ctrl.$setViewValue(html);
                        });
                    };
                    editor.create();
                }
            };
        })
})(angular)