package com.skypiea.system.service.impl;

import com.skypiea.common.cons.UserConstants;
import com.skypiea.common.result.SPResult;
import com.skypiea.system.mapper.UserMapper;
import com.skypiea.system.model.UserInfo;
import com.skypiea.system.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 作者: huangwenjian
 * 描述: 处理admin账号登录
 * 创建时间: 2017-03-29 19:11
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SPResult login(String username, String password, HttpServletRequest request) {

        UserInfo user = userMapper.findUserByName(username);
        if (user == null) {
            return SPResult.fail(UserConstants.NO_SUCH_USER);
        }
        //获取数据库中的密码
        String dbPassword = userMapper.findPasswordByUsername(username);
        if (!StringUtils.equals(password, dbPassword)) {
            //如果密码不相等
            return SPResult.fail(UserConstants.PASSWORD_ERROR);
        }
        //用户名,密码都能匹配上,则登录成功,存到session中
        HttpSession session = request.getSession();
        session.setAttribute("admin", user);
        return SPResult.ok();
    }
}
