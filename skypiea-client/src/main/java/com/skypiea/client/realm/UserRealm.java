package com.skypiea.client.realm;

import com.skypiea.system.mapper.UserMapper;
import com.skypiea.system.model.UserInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-25 22:20
 */
public class UserRealm extends AuthorizingRealm {

    public static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserMapper userMapper;

    private UserInfo userInfo;
    private IUserCallback callback;

    /**
     * 用于授权的方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("username:" + username);

        //通过username去数据库查询用户角色和角色所对应的权限

        HashSet<String> roleSet = new HashSet<>();
        roleSet.add("role1");
        roleSet.add("role2");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleSet);
        return info;
    }

    /**
     * 用于登录认证的方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户登录的用户名
        String inputUsername = (String) authenticationToken.getPrincipal();
        logger.info("doGetAuthenticationInfo:当前用户登录时输入的username<==" + inputUsername);

        //定义变量,表示从数据库查到的正确的username和password
        String dbUsername = null;
        String dbPassword = null;
        UserInfo user = userMapper.findUserByName(inputUsername);
        if (user == null) {
            //如果用户没有找到,返回null
            return null;
        } else {
            //如果通过用户名查找到对应的用户,则调用回调方法
            if (callback != null) {
                callback.onUserCallback(user);
            }
            dbUsername = user.getUsername();
        }
        //如果找到了该用户,则通过用户名去数据库查询对应的密码
        dbPassword = userMapper.findPasswordByUsername(dbUsername);
        //构造正确的认证信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(dbUsername, dbPassword, getName());
        return info;
    }

    /**
     * 设置回调函数
     *
     * @param callback
     */
    public void setOnUserCallback(IUserCallback callback) {
        this.callback = callback;
    }

    public interface IUserCallback {
        void onUserCallback(UserInfo userInfo);
    }

//    /**
//     * 设置用户信息
//     *
//     * @param userInfo
//     */
//    public void setUserInfo(UserInfo userInfo) {
//        this.userInfo = userInfo;
//    }
//
//    public UserInfo getUserInfo() {
//        return userInfo;
//    }
}
