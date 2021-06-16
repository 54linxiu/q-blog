package com.qht.config;

import com.qht.entity.UserAdmin;
import com.qht.service.UserAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * shiro中自定义realm一般继承AuthorizingRealm，
 * 然后实现getAuthenticationInfo和getAuthorizationInfo方法，来完成登录和权限的验证。
 * @ClassName UserRealm
 * @Author q
 * @Date 2021/6/16 22:43
 * @Version 1.0
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserAdminService userAdminService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        UserAdmin currentUser = (UserAdmin)subject.getPrincipal();//拿到user对象

        //设置当前用户的权限
        info.addStringPermission("perms:"+currentUser.getLocked());
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        userToken.getUsername();
        return null;
    }
}
