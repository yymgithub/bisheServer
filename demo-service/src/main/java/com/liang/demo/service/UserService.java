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
}
