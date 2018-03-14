package com.liang.demo.service.impl;

import com.liang.demo.dao.UserMapper;
import com.liang.demo.domain.User;
import com.liang.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
}
