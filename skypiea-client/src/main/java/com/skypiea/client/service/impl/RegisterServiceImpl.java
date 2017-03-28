package com.skypiea.client.service.impl;

import com.skypiea.client.service.CheckService;
import com.skypiea.client.service.RegisterService;
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
 * 创建时间: 2017-03-22 15:37
 */

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CheckService checkService;

    @Override
    public SPResult register(UserInfo userInfo) {
        String username = userInfo.getUsername();
        //校验用户名
        if (StringUtils.isNotEmpty(username)) {
            SPResult result = checkService.checkRegisterData(username, 1);
            if (!(boolean) result.getData()) {      //如果为false,代表用户已存在
                return SPResult.fail(UserConstants.USER_ALREADY_EXIST);
            }
        } else {
            return SPResult.fail("用户名不能为空");
        }
        //校验通过,将用户存入数据库
        userMapper.addUser(userInfo);
        return SPResult.ok("注册成功");
    }
}
