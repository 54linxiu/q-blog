package com.qht.service;

import com.qht.entity.UserAdmin;

public interface UserAdminService {


    int createUser(UserAdmin userAdmin);

    UserAdmin login(String account,String password);

    UserAdmin queryUser(String account);
}
