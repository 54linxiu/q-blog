package com.qht;

import com.qht.entity.UserAdmin;
import com.qht.service.UserAdminService;
import com.qht.service.impl.UserAdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QBlogApplicationTests {
    @Autowired
    UserAdminService userAdminService;
    @Test
    void contextLoads() {
        userAdminService.createUser(new UserAdmin("2410480275", "林修", "123456", "", 0));
    }

}
