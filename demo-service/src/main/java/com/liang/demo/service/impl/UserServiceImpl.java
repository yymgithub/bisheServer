package com.liang.demo.service.impl;

import com.liang.demo.dao.UserMapper;
import com.liang.demo.domain.User;
import com.liang.demo.service.UserService;
import com.liang.demo.util.BaseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
