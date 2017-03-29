package com.skypiea.system.service.impl;

import com.skypiea.common.cons.UserConstants;
import com.skypiea.common.result.SPResult;
import com.skypiea.common.utils.TokenUtils;
import com.skypiea.system.mapper.AdminMapper;
import com.skypiea.system.model.UserInfo;
import com.skypiea.system.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * 作者: huangwenjian
 * 描述: 处理admin账号登录
 * 创建时间: 2017-03-29 19:11
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public SPResult login(String username, String password, HttpSession session) {

        UserInfo user = adminMapper.findAdminByName(username);
        if (user == null) {
            return SPResult.fail(UserConstants.NO_SUCH_USER);
        }
        //获取数据库中的密码
        String dbPassword = adminMapper.findPasswordByUsername(username);
        if (!StringUtils.equals(password, dbPassword)) {
            //如果密码不相等
            return SPResult.fail(UserConstants.PASSWORD_ERROR);
        }
        //用户名,密码都能匹配上,则登录成功,创建token
        String accessToken = TokenUtils.createToken();
        session.setAttribute("session_key:" + accessToken, user);
        return SPResult.ok(accessToken);
    }

    @Override
    public SPResult getUserByToken(String token, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("session_key:" + token);
        if (user == null) {
            return SPResult.fail(UserConstants.NO_SUCH_USER);
        }
        return SPResult.ok(user);
    }
}
