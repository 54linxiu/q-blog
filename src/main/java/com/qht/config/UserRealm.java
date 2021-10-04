package com.qht.config;

import com.qht.entity.UserAdmin;
import com.qht.service.UserAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

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
//        System.out.println("授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        UserAdmin currentUser = (UserAdmin)subject.getPrincipal();//拿到user对象



        //设置当前用户的权限
        info.addStringPermission("perms:"+ currentUser.getLocked());
        info.addStringPermission("edit:"+ (currentUser.getLocked()<=1));

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
//        System.out.println("认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        // 从数据库获取用户信息
        UserAdmin user  = userAdminService.queryUser(userToken.getUsername());
        if (user == null) {
            System.out.println("用户不存在");
            return null;
        }
        // 密码认证，shiro做
        return new SimpleAuthenticationInfo(user,user.getUserPwd(),"");
    }
}
