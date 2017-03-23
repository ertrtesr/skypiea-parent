(function (angular) {
    'use strict';

    angular.module('app').controller('userManagerController', ['$scope', 'userManagerService', function ($scope, userManagerService) {

        console.log("--------userManagerController Start!!--------");

        var constants = {
            USER_ALREADY_EXIST: '用户已存在',
            USER_ADD_SUCCESS: '用户添加成功',
            USER_UPDATE_SUCCESS: '用户修改成功',
            USER_DELETE_SUCCESS: '用户删除成功',
            NO_SUCH_USER: '用户不存在',
            ADD: '添加',
            EDIT: '修改'
        };

        $scope.userList = [];
        $scope.loading = true;          //是否显示加载进度条
        $scope.isEditMode = false;      //是否是编辑模式

        $scope.btnText = '';            //模态框按钮的文字
        $scope.userEditTitle = '';      //模态框标题

        $scope.userData = {
            username: '',
            password: '',
            authorization: ''
        };

        //定义获取用户列表的方法
        var getUserList = function () {
            userManagerService.getUserList(function (resp) {
                $scope.userList = resp.data.data;
                $scope.loading = false; //加载完成以后隐藏进度条
            });
        }

        //获取用户列表
        getUserList();


        //获取用户列表
        userManagerService.getUserList(function (resp) {
            $scope.userList = resp.data.data;
            $scope.loading = false; //加载完成以后隐藏进度条
        });

        //点击右上角"用户添加"按钮时调用的方法
        $scope.addForm = function (text) {
            $scope.isEditMode = false;
            $scope.btnText = text;
            $scope.userData.username = '';
            $scope.userData.password = '';
            $scope.userData.authorization = '';
        }

        //点击"操作"中"用户编辑"按钮时调用的方法
        $scope.editForm = function (text, user) {
            $scope.isEditMode = true;           //设置为编辑模式
            $scope.btnText = text;
            $scope.userData.username = user.username;
            $scope.userData.password = user.password;
            $scope.userData.authorization = user.authorization;
        }

        //提交用户表单
        $scope.submitForm = function (userData) {
            //如果点击的模态框的提交按钮为"添加"
            if ($scope.btnText == constants.ADD) {
                userManagerService.addUser(userData, function (resp) {
                    var msg = resp.data.msg;
                    if (msg == constants.USER_ALREADY_EXIST) {        //用户已存在
                        //用户添加失败
                        swal({
                            title: '警告',
                            text: msg,
                            type: 'error'
                        });
                    } else {    //用户添加成功
                        swal({
                            title: '提示',
                            text: constants.USER_ADD_SUCCESS,
                            type: 'success'
                        }, function () {
                            //点击了sweetAlert的确定按钮后会走这个回调方法
                            //先显示加载进度条
                            $scope.loading = true;
                            //隐藏模态框
                            $('#user-edit').modal('hide');          //此处用jQuery的dom操作
                            getUserList();
                        });
                    }
                });
            } else if ($scope.btnText == constants.EDIT) {  //如果点击的模态框的提交按钮为"修改"
                userManagerService.updateUser(userData, function (resp) {
                    if (resp.data != null) {
                        swal({
                            title: '提示',
                            text: constants.USER_UPDATE_SUCCESS,
                            type: 'success'
                        }, function () {
                            //隐藏模态框
                            $('#user-edit').modal('hide');
                            getUserList();      //重新获取列表
                        });
                    }
                });
            }
        }

        $scope.deleteUser = function (user) {
            //swal嵌套的时候,要设置closeOnConfirm为false
            swal({
                title: '',
                text: '确定删除吗',
                type: 'warning',
                showCancelButton: 'true',
                showConfirmButton: 'true',
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                closeOnConfirm: false
            }, function () {
                userManagerService.deleteUser(user, function (resp) {
                    if (resp.data) {
                        swal({
                            title: '',
                            text: constants.USER_DELETE_SUCCESS,
                            type: 'success'
                        }, function () {
                            $scope.loading = true;
                            getUserList();
                        });
                    } else {
                        //提示删除失败,无此用户
                        swal('警告', constants.NO_SUCH_USER, 'error');
                    }
                });
            });
        }

    }]);
})(angular)