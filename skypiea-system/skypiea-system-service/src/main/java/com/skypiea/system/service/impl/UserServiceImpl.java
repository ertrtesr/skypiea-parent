package com.skypiea.system.service.impl;

import com.skypiea.common.cons.UserConstants;
import com.skypiea.common.http.HttpStatus;
import com.skypiea.common.result.SPResult;
import com.skypiea.system.mapper.UserMapper;
import com.skypiea.system.model.UserInfo;
import com.skypiea.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-02-20 19:05
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public SPResult findUserById(int id) {
        UserInfo user = userMapper.findUserById(id);
        if (user != null) {
            return SPResult.ok(user);
        } else {
            return SPResult.build(HttpStatus.OK, UserConstants.NO_SUCH_USER);
        }
    }

    @Override
    public SPResult findUserByName(String username) {
        UserInfo user = userMapper.findUserByName(username);
        return SPResult.ok(user);
    }

    @Override
    public SPResult findAllUsers() {
        List<UserInfo> users = userMapper.findAllUsers();
        return SPResult.ok(users);
    }

    @Override
    public SPResult checkPasswordByUsername(String username, String password) {
        String rightPwd = userMapper.findPasswordByUsername(username);       //正确的密码
        if (!rightPwd.equals(password)) {
            return SPResult.build(HttpStatus.OK, UserConstants.PASSWORD_ERROR);
        } else {
            return SPResult.build(HttpStatus.OK, UserConstants.PASSWORD_RIGHT);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SPResult addUser(UserInfo newUser) {
        String username = newUser.getUsername();
        UserInfo user = userMapper.findUserByName(username);
        if (user != null) {       //如果用户已存在,则返回已存在的json
            return SPResult.build(HttpStatus.OK, UserConstants.USER_ALREADY_EXISTED);
        } else {
            //如果数据库中不存在该用户,则存入数据库
            userMapper.addUser(newUser);
            return SPResult.ok(newUser);
        }
    }

    @Override
    public SPResult updateUser(UserInfo newUser) {
        String username = newUser.getUsername();
        UserInfo user = userMapper.findUserByName(username);
        if (user != null) {  //如果用户名存在,则更新
            userMapper.updateUser(newUser);
            return SPResult.ok(newUser);
        } else {
            //如果用户不存在,则返回不存在
            return SPResult.build(HttpStatus.OK, UserConstants.NO_SUCH_USER);
        }
    }

    @Override
    public boolean deleteUser(String username) {
        boolean result = true;
        try {
            userMapper.deleteUser(username);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
