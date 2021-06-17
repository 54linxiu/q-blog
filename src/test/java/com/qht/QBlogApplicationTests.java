package com.qht;

import com.qht.entity.UserAdmin;
import com.qht.service.UserAdminService;
import com.qht.service.impl.UserAdminServiceImpl;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QBlogApplicationTests {
    @Autowired
    UserAdminService userAdminService;
    @Test
    void contextLoads() {
//        userAdminService.createUser(new UserAdmin("2410480275", "林修", "123456", "", 0));
// 创建一个密码验证算法捕获器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置密码验证为MD5加密验证
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 加密次数为 1 次
        hashedCredentialsMatcher.setHashIterations(1);

        SimpleHash simpleHash = new SimpleHash("MD5","123456");

        System.out.println(simpleHash.toString());
        System.out.println(userAdminService.queryUser("2410480275"));
    }


}
