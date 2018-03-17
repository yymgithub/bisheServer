package com.liang.demo.dao;

import com.liang.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 管理员创建用户
     * @param user
     * @return
     */
    Integer createdUser(User user);

}
