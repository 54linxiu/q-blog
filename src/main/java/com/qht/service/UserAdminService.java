package com.qht.service;

import com.qht.dao.UserAdminMapper;
import com.qht.entity.UserAdmin;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserAdminService {


    int createUser(UserAdmin userAdmin);

    UserAdmin login(String account,String password);

    UserAdmin queryUser(String account);
}
