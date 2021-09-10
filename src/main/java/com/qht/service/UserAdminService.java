package com.qht.service;

import com.qht.entity.UserAdmin;

public interface UserAdminService {


    int createUser(UserAdmin userAdmin);

    UserAdmin login(String account,String password);

    UserAdmin queryUser(String account);

    /**
     * 修改用户
     * @param userAdmin
     * @return
     */
    int update(UserAdmin userAdmin);

    /**
     * 保存头像
     * @param filename
     */
    void savaPhoto(String filename,String userId);
}
