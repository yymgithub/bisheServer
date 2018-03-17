package com.liang.demo.web.controller;

import com.liang.demo.domain.User;
import com.liang.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xujinliang on 2017/12/7
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private UserService userService;

    public static final Logger logger = LogManager.getLogger(TestController.class);

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(){
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return "/vm/screen/index.vm";
    }
}
