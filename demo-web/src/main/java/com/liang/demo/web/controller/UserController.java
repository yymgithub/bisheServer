package com.liang.demo.web.controller;

import com.liang.demo.domain.Result;
import com.liang.demo.domain.User;
import com.liang.demo.service.UserService;
import com.liang.demo.util.BaseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    public static final Logger logger = LogManager.getLogger(UserController.class);

    @RequestMapping(value = "/createdUser")
    @ResponseBody
    public Result createUserByyym(String phoneId, String userName, Integer userRole, String password) {
        Result result = new Result();
        //BaseUtil.isNullOrEmpty相当于 == null || .equals("")
        if (BaseUtil.isNullOrEmpty(phoneId) || BaseUtil.isNullOrEmpty(password) || userRole == null || userRole > 3 || userRole < 1) {
            result.setSuccess(false);
            result.setCode(2);
            result.setMessage("输入参数错误，请重新输入");
            return result;
        }
        try {
            Integer suc = userService.createdUser(new User(phoneId, userName, userRole, password));
            if (suc == 1) {
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("通信成功");
            } else {
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("创建失败");
            }
        } catch (Error error) {
            logger.error("创建用户的Controller层出现异常", error);
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("创建用户的Controller层出现异常");
        }
        return result;
    }


    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public String userLogin(HttpServletRequest request, Model model, @ModelAttribute("user")User user){

        System.out.println(user.toString());
        return "screen/index";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public ModelAndView login(HttpServletRequest request, Model model){
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }
}
