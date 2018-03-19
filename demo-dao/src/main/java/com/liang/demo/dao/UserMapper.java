package com.liang.demo.dao;

import com.liang.demo.domain.User;
import org.omg.PortableInterceptor.INACTIVE;
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

    /**
     * 用户登陆判定
     * @param user 用户名 密码
     * @return user
     */
    User selectUserByPhonePw(User user);

    /**
     * 用户登陆或退出修改用户状态
     * @param user
     * @return
     */
    Integer updateUserstate(User user);

}
