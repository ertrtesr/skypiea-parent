package com.skypiea.client.config;

import com.skypiea.client.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-24 16:23
 */

@Configuration
public class ShiroConfiguration {

    Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

//    @Bean(name = "shiroFilter")
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
//        logger.info("注入Shiro的Web过滤器-->shiroFilter");
//
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        //Shrio的核心安全接口,这个属性是必须的
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        //要求登录时的链接(可根据项目的url进行替换),非必须的属性,默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        //登录成功后要跳转的链接,逻辑也可以自定义,例如返回上次请求的页面
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//
//        /*定义shiro过滤链 Map结构 * Map中key(xml中是指value值)的第一个'/'代表的路径
//          是相对于HttpServletRequest.getContextPath()的值来的 * anon：它对应的过滤器里面是空的,
//          什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种 * authc：该过滤器下的
//          页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter */
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/logout", "logout");
//        //过滤链定义，从上向下顺序执行，一般将 /**放在最为下边:这是一个坑呢，一不小心代码就不好使了;
//        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
//        filterChainDefinitionMap.put("/webui/**", "anon");
//        filterChainDefinitionMap.put("/webjars/**", "anon");
//        filterChainDefinitionMap.put("/login", "anon");
//        filterChainDefinitionMap.put("/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(UserRealm userRealm) {
        logger.info("注入Shiro的核心安全管理类-->securityManager");
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public Subject getSubject(SecurityManager securityManager) {
        logger.info("注入主体-->subject");
        SecurityUtils.setSecurityManager(securityManager);
        return SecurityUtils.getSubject();
    }

    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setName("userRealm");
        return userRealm;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
