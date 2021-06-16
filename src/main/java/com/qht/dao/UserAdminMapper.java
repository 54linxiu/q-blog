package com.qht.dao;

import com.qht.entity.UserAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserAdminMapper {
    /**
     * 创建用户
     * @param userAdmin
     * @return
     */
    int createUser( UserAdmin userAdmin);

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    UserAdmin login(@Param("account") String userName,@Param("Password") String password);

    /**
     * 查询用户
     * @param account
     * @return
     */
    UserAdmin queryUser(@Param("account") String account);

}
