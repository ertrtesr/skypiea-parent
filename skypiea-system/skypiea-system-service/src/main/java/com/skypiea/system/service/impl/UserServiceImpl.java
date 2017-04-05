package com.skypiea.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.skypiea.common.cons.UserConstants;
import com.skypiea.common.http.HttpStatus;
import com.skypiea.common.result.SPResult;
import com.skypiea.common.utils.ExceptionUtils;
import com.skypiea.system.mapper.UserMapper;
import com.skypiea.system.model.UserInfo;
import com.skypiea.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public SPResult getUserById(int id) {
        UserInfo user = userMapper.getUserById(id);
        if (user != null) {
            return SPResult.ok(user);
        } else {
            return SPResult.build(HttpStatus.OK, UserConstants.NO_SUCH_USER);
        }
    }

    @Override
    public SPResult getUserByName(String username) {
        UserInfo user = userMapper.getUserByName(username);
        return SPResult.ok(user);
    }

    @Override
    public SPResult getAllUsers() {
        List<UserInfo> users = userMapper.getAllUsers();
        return SPResult.ok(users);
    }

    @Override
    public SPResult getUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> users = userMapper.getAllUsers();
        return SPResult.ok(users);
    }

    @Override
    public int getUserCount() {
        int count = userMapper.getUserCount();
        return count;
    }

    @Override
    public SPResult checkPasswordByUsername(String username, String password) {
        String rightPwd = userMapper.getPasswordByUsername(username);       //正确的密码
        if (!rightPwd.equals(password)) {
            return SPResult.build(HttpStatus.OK, UserConstants.PASSWORD_ERROR);
        } else {
            return SPResult.build(HttpStatus.OK, UserConstants.PASSWORD_RIGHT);
        }
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public SPResult addUser(UserInfo user) {
        String username = user.getUsername();
        UserInfo dbUser = userMapper.getUserByName(username);
        if (dbUser != null) {       //如果用户已存在,则返回已存在的json
            return SPResult.fail(UserConstants.USER_ALREADY_EXIST);
        } else {
            //如果数据库中不存在该用户,则存入数据库
            try {
                //这两步操作要放在一个事务中
                userMapper.addUser(user);           //往t_user表中添加数据
                userMapper.addUserRole(user);       //往t_user_role表中添加关系数据
                return SPResult.ok(user);
            } catch (Exception e) {
                e.printStackTrace();
                return SPResult.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    /**
     * 更新用户数据
     *
     * @param newUser 新的用户,但是id和username是不能更改的
     * @return
     */
    @Override
    @Transactional
    public SPResult updateUser(UserInfo newUser) {
        String username = newUser.getUsername();
        UserInfo user = userMapper.getUserByName(username);
        if (user != null) {
            //如果用户名存在,则更新
            //1.更新用户表
            userMapper.updateUser(newUser);
            //2.更新用户-角色关系表
            userMapper.updateUserRole(newUser);
            return SPResult.ok(newUser);
        } else {
            //如果用户不存在,则返回不存在
            return SPResult.build(HttpStatus.OK, UserConstants.NO_SUCH_USER);
        }
    }

    /**
     * 根据用户名删除用户
     *
     * @param username
     * @return
     */
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
