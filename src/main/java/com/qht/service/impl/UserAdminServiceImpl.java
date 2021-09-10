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


    private UserAdminMapper userAdminMapper;

    @Autowired
    public void setUserAdminMapper(UserAdminMapper userAdminMapper) {
        this.userAdminMapper = userAdminMapper;
    }

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

    @Override
    public int update(UserAdmin userAdmin) {
        if(userAdmin.getUserAccount() != null && userAdmin.getUserId() != 0){

            return userAdminMapper.update(userAdmin);
        }
        return 0;
    }

    @Override
    public void savaPhoto(String filename,String userId) {
        userAdminMapper.savaPhoto(filename,userId);
    }


}
