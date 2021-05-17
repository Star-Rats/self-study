package com.jmy.shiro.config;

import com.jmy.shiro.model.User;
import com.jmy.shiro.service.UserService;
import com.jmy.shiro.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomerRealm extends AuthorizingRealm {

    // 授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    // 认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("开始认证");
        String username = (String) token.getPrincipal();
        // 使用上下文工具类获取userService
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findByUsername(username);
        if (null != user) {
            // 将用户信息返回给shiro进行认证
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt().getBytes()),this.getName());
        }
        return null;
    }
}
