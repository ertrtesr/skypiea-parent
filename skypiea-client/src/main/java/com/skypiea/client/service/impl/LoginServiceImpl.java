package com.skypiea.client.service.impl;

import com.skypiea.client.cache.StringRedisCache;
import com.skypiea.client.realm.UserRealm;
import com.skypiea.client.service.CheckService;
import com.skypiea.client.service.LoginService;
import com.skypiea.client.service.PermissionService;
import com.skypiea.common.cons.UserConstants;
import com.skypiea.common.http.HttpMsg;
import com.skypiea.common.result.SPResult;
import com.skypiea.common.utils.JsonUtils;
import com.skypiea.common.utils.TokenUtils;
import com.skypiea.system.model.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 作者: huangwenjian
 * 描述: 登录
 * 创建时间: 2017-03-23 09:50
 */

@Service
@PropertySource("classpath:resource.properties")
public class LoginServiceImpl implements LoginService {

    @Value("${REDIS_TOKEN_KEY}")
    private String REDIS_TOKEN_KEY;

    @Value("${TOKEN_EXPIRE_TIME}")
    private int TOKEN_EXPIRE_TIME;

    @Autowired
    private CheckService checkService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private StringRedisCache redisCache;

    @Autowired
    private Subject subject;

    @Autowired
    UserRealm userRealm;

    private UserInfo user;

    @PostConstruct
    public void init() {
        //在初始化方法中设置回调
        userRealm.setOnUserCallback(new UserRealm.IUserCallback() {
            @Override
            public void onUserCallback(UserInfo userInfo) {
                user = userInfo;
            }
        });
    }

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
    public SPResult loginByShiro(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            if (subject != null)
                subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return SPResult.fail(UserConstants.NO_SUCH_USER);
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return SPResult.fail(UserConstants.PASSWORD_ERROR);
        }
        //登录成功

        //生成accessToken
        String accessToken = TokenUtils.createToken();
        //将user对象序列化成json写入redis
        String redisKey = REDIS_TOKEN_KEY + ":" + accessToken;
        if (user != null) {
            redisCache.add(redisKey, JsonUtils.objectToJson(user));
            //设置token的失效时间
            redisCache.expire(redisKey, TOKEN_EXPIRE_TIME);
            //将accessToken返回给客户端
            return SPResult.ok(accessToken);
        } else {
            return SPResult.error("服务器初始化user数据失败");
        }
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
        redisCache.delete(REDIS_TOKEN_KEY + ":" + token);
        return SPResult.ok();
    }

    @Override
    public SPResult logoutByShiro(String token) {
        if (subject != null)
            subject.logout();
        redisCache.delete(REDIS_TOKEN_KEY + ":" + token);
        return SPResult.ok();
    }
}
