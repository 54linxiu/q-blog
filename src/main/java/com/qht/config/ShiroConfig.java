package com.qht.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * @ClassName ShiroConfig
 * @Author q
 * @Date 2021/6/16 22:47
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建 ShiroFilterFactoryBean
     */
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权，正常情况下，没有授权会跳转到未授权页面
//        filterMap.put("/user/add","perms[perms:add]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //设置登录的请求
        shiroFilterFactoryBean.setLoginUrl("/admin/login");

        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/noauto");

        return shiroFilterFactoryBean;
    }

        /**
         * 创建 DefaultWebSecurityManager
         * @Qualifier 注解 表示传入的参数是下边那个放入spring容器中的bean
         */
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 这里要吧 userRealm 和 securityManager 关联
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建 Realm
     * @Bean 的作用： 将该方法返回的对象放入spring容器， 以便给上边的方法使用
     */
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}