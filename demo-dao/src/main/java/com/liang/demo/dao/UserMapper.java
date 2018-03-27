package com.liang.demo.dao;

import com.liang.demo.domain.User;
import org.apache.ibatis.annotations.Param;
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
    List<User> selectUserByPhonePw(User user);

    /**
     * 用户登陆或退出修改用户状态
     * @param user
     * @return
     */
    Integer updateUserstate(User user);

    /**
     * 获得数据库表的所有User
     * @return
     */
    List<User> getALLUser();
    /**
     * 通过id获取用户信息
     */
    List<User> getUserById(@Param("id") Integer id);
    /**
     * 通过phoneId获取用户信息
     */
    List<User> getUserByPhoneId(@Param("phoneId") String phoneId);

    /**
     * 通过Id更新用户是否有效
     * @param user
     * @return
     */
    Integer updateUserYnById(User user);
    /**
     * 通过修改用户界面实现用户信息修改
     * @param user
     * @return
     */
    Integer updateUser(User user);

}
