package com.skypiea.client.service.impl;

import com.skypiea.client.cache.StringRedisCache;
import com.skypiea.client.service.CheckService;
import com.skypiea.client.service.LoginService;
import com.skypiea.common.cons.UserConstants;
import com.skypiea.common.http.HttpMsg;
import com.skypiea.common.result.SPResult;
import com.skypiea.common.utils.JsonUtils;
import com.skypiea.common.utils.TokenUtils;
import com.skypiea.system.model.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * 作者: huangwenjian
 * 描述: 登录
 * 创建时间: 2017-03-23 09:50
 */

@Service
@PropertySource("classpath:resource.properties")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CheckService checkService;

    @Autowired
    private StringRedisCache redisCache;

    @Value("${REDIS_TOKEN_KEY}")
    private String REDIS_TOKEN_KEY;

    @Value("${TOKEN_EXPIRE_TIME}")
    private int TOKEN_EXPIRE_TIME;

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
            redisCache.expire(redisKey, TOKEN_EXPIRE_TIME);
            //将token返回给客户端
            return SPResult.ok(token);
        }
        return SPResult.fail("登录失败");
    }

    @Override
    public SPResult getUserByToken(String token) {
        //根据token取用户信息
        String json = redisCache.getValue(REDIS_TOKEN_KEY + ":" + token);
        //判断是否查询到结果
        if (StringUtils.isEmpty(json)) {
            return SPResult.fail(UserConstants.USER_TOKEN_NOT_EXIST);
        }
        //序列化为user对象
        UserInfo user = JsonUtils.jsonToObject(json, UserInfo.class);
        //更新redis中token的过期时间
        redisCache.expire(REDIS_TOKEN_KEY + ":" + token, TOKEN_EXPIRE_TIME);
        return SPResult.ok(user);
    }

    @Override
    public SPResult logout(String token) {
        //删除redis中的token
        redisCache.delete(token);
        return SPResult.ok();
    }
}
