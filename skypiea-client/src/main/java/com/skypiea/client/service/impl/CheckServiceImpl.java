package com.skypiea.client.service.impl;

import com.skypiea.client.service.CheckService;
import com.skypiea.common.cons.UserConstants;
import com.skypiea.common.result.SPResult;
import com.skypiea.system.mapper.UserMapper;
import com.skypiea.system.model.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-22 15:53
 */

@Service
public class CheckServiceImpl implements CheckService {

    private static final int USERNAME_TYPE = 1;
    private static final int PHONE_TYPE = 2;
    private static final int EMAIL_TYPE = 3;

    @Autowired
    private UserMapper userMapper;

    @Override
    public SPResult checkRegisterData(String param, int type) {
        UserInfo user = null;
        //查找是否有已经注册的用户
        if (type == USERNAME_TYPE) {        //检查用户名
            user = userMapper.findUserByName(param);
        } else if (type == PHONE_TYPE) {    //检查电话

        } else if (type == EMAIL_TYPE) {    //检查邮箱

        }
        if (user != null) {     //说明用户名,电话,邮箱已被注册
            return SPResult.ok(false);
        }
        return SPResult.ok(true);
    }

    @Override
    public SPResult checkLoginData(String username, String password) {
        //根据用户名取用户信息
        UserInfo user = userMapper.findUserByName(username);
        if (user == null) {
            //如果用户为空
            return SPResult.fail(UserConstants.NO_SUCH_USER);
        }
        //根据用户名获取数据库中保存的密码,跟用户输入的密码进行比对
        String savedPassword = userMapper.findPasswordByUsername(username);
        if (!StringUtils.equals(savedPassword, password)) {
            //密码错误,登录失败
            return SPResult.fail(UserConstants.PASSWORD_ERROR);
        }
        //用户名密码校验通过,将user包装后返回SPResult
        return SPResult.ok(user);
    }
}
