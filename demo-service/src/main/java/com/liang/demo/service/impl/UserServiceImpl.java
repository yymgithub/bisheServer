package com.liang.demo.service.impl;

import com.liang.demo.dao.UserMapper;
import com.liang.demo.domain.User;
import com.liang.demo.service.UserService;
import com.liang.demo.util.BaseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;


    /**
     * 管理员创建新的用户
     *
     * @param user 新的用户
     * @return
     */
    @Override
    public Integer createdUser(User user) {
        //判断参数是否合法
        if (user == null) {
            return 0;
        }
        Integer result = 0;
        try {
            result = userMapper.createdUser(user);
        } catch (Error e) {
            logger.error("创建用户的service层异常", e);
        }
        return result;
    }

    /**
     * 判断输入密码和账号在数据库中是否有相应数据
     *
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        if (user == null) return null;
        try {
            List<User> us = userMapper.selectUserByPhonePw(user);
            if (us == null || us.size() != 1) return null;
            else return us.get(0);
        } catch (Error e) {
            logger.error("用户登陆service层异常", e);
        }
        return null;
    }

    @Override
    public boolean updateUserState(User user) {
        if(user==null) return false;
        try{
            if(userMapper.updateUserstate(user)==1) return true;
        }catch (Error e){
            logger.error("用户登陆service层修改用户状态异常", e);
        }
        return false;
    }

    /**
     * 获得数据库中所有用户数据
     *
     * @return
     */
    @Override
    public List<User> getAllUser() {
        List<User> userList=new ArrayList<>();
        try{
            userList=userMapper.getALLUser();

        }catch (Error e){
            logger.error("查看用户service层异常", e);
        }
        return userList;

    }

    /**
     * 获取数据库数据通过id
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        if (id == null) return null;
        try {
            /*List<User> us =null;*/
            List<User> us= userMapper.getUserById(id);
            if (us == null || us.size() != 1) return null;
            else return us.get(0);
        } catch (Throwable e) {
            logger.error("修改用户信息时service层异常", e);
        }
        return null;
    }

    @Override
    public User getUserByPhoneId(String phoneId) {
        if (phoneId == null) return null;
        try {
            /*List<User> us =null;*/
            List<User> us= userMapper.getUserByPhoneId(phoneId);
            if (us == null || us.size() != 1) return null;
            else return us.get(0);
        } catch (Throwable e) {
            logger.error("修改用户信息时service层异常", e);
        }
        return null;
    }

    @Override
    public boolean updateUserYnById(User user) {
        if(user==null) return false;
        try{
            Integer result=userMapper.updateUserYnById(user);
            if(result==1) return true;
        }catch(Throwable e){
            logger.error("停用用户信息时service层异常", e);
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if(user==null) return false;
        try{
            Integer result=userMapper.updateUser(user);
            if(result==1) return true;
        }catch(Throwable e){
            logger.error("停用用户信息时service层异常", e);
        }
        return false;
    }
}
