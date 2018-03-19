package com.liang.demo.service;

import com.liang.demo.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 管理员创建新的用户
     * @param user 新的用户
     * @return
     */
    Integer createdUser(User user);

    /**
     * 判断输入密码和账号在数据库中是否有相应数据
     * @param user
     * @return
     */
    User userLogin(User user);

    /**
     * 账户密码登录后更新账户状态
     * @param user
     * @return
     */
    boolean updateUserState(User user);

}
