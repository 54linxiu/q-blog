package com.qht.service.impl;

import com.qht.dao.UserAdminMapper;
import com.qht.entity.UserAdmin;
import com.qht.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @ClassName UserAdminServiceImpl
 * @Author linxiu
 * @Date 2021/6/15 19:21
 * @Version 1.0
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    UserAdminMapper userAdminMapper;



    @Override
    public int createUser(UserAdmin userAdmin) {
        userAdmin.setUserPwd(DigestUtils.md5DigestAsHex(userAdmin.getUserPwd().getBytes()));
        return userAdminMapper.createUser(userAdmin);
    }

    @Override
    public UserAdmin login(String account, String password) {
        return null;
    }

    @Override
    public UserAdmin queryUser(String account) {
        return userAdminMapper.queryUser(account);
    }


}
