package com.skypiea.client.service.impl;

import com.skypiea.client.cache.StringRedisCache;
import com.skypiea.client.service.CheckService;
import com.skypiea.client.service.LoginService;
import com.skypiea.common.http.HttpMsg;
import com.skypiea.common.result.SPResult;
import com.skypiea.common.utils.JsonUtils;
import com.skypiea.common.utils.TokenUtils;
import com.skypiea.system.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者: huangwenjian
 * 描述: 登录
 * 创建时间: 2017-03-23 09:50
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CheckService checkService;

    @Autowired
    private StringRedisCache redisCache;

    private static final String REDIS_TOKEN_KEY = "redis_token";

    @Override
    public SPResult login(String username, String password) {

        SPResult result = checkService.checkLoginData(username, password);
        if (result.getMsg() == HttpMsg.OK) {
            //登录成功,生成access_token,写入到redis中
            String token = TokenUtils.createToken();
            UserInfo user = (UserInfo) result.getData();
            //将user对象序列化为json字符串后写入
            String redisKey = REDIS_TOKEN_KEY + ":" + token;
            redisCache.add(redisKey, JsonUtils.objectToJson(user));
            //设置redis_session的过期时间为30分钟
            redisCache.expire(redisKey, 30 * 60);
            //将token返回给客户端
            return SPResult.ok(token);
        }
        return SPResult.fail("登录失败");
    }
}
