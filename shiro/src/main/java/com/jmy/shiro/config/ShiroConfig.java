package com.jmy.shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    // shiroFilter
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/user/**","anon"); // 不拦截用户相关路径
        map.put("/index/**","authc"); // 任意路径都需要认证授权
        // 为过滤器配置安全规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        // 为过滤器设置认证页面
        shiroFilterFactoryBean.setLoginUrl("/user/login/");
        return shiroFilterFactoryBean;
    }
    // 安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }
    // Ream
    @Bean
    public Realm getRealm(){
        CustomerRealm realm = new CustomerRealm();
        // 修改Realm的密码匹配器为Hash密码匹配器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置加密算法为MD5
        matcher.setHashAlgorithmName("md5");
        // 设置散列次数
        matcher.setHashIterations(1024);
        realm.setCredentialsMatcher(matcher);

        // 缓存管理器
        /*realm.setAuthenticationCachingEnabled(true);
        realm.setAuthorizationCachingEnabled(true);*/
        realm.setCacheManager(new EhCacheManager());
        return realm;
    }
}
