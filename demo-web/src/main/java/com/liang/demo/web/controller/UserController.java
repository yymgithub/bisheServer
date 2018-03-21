package com.liang.demo.web.controller;

import com.liang.demo.domain.Menu;
import com.liang.demo.domain.Pager;
import com.liang.demo.domain.Result;
import com.liang.demo.domain.User;
import com.liang.demo.service.UserService;
import com.liang.demo.util.BaseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping(value = "/appLogin")
    @ResponseBody
    public String userLogin(String phoneId, String password) {

//        System.out.println(user.toString());
        return "screen/index";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public ModelAndView login(@ModelAttribute("user") User user) {
//        HttpSession session= ServletActionContext.SESSION_SCOPE

        ModelAndView view = new ModelAndView();
        User us = userService.userLogin(user);
        if (us == null || us.getUserRole() != 3) {
            view.setViewName("../../login");
            return view;
        }
//        us.setUserState(1);
//        boolean result=userService.updateUserState(us);
//        if(!result){
//            view.setViewName("../../login");
//            return view;
//        }

        view.addObject("user", us);
//          httpSession.setAttribute("user",us);
        //界面显示Menu初始化代码
        List<Menu> menuVoList = new ArrayList<>();
        //创建用户Menu
        Menu userMenu = new Menu();
        userMenu.setMenuName("用户管理");
        userMenu.setMenuLink("");
        //创建二级目录
        Menu userMenuOne = new Menu();
        userMenuOne.setMenuLink("/user/showUser");
        userMenuOne.setMenuName("查看用户");
        Menu userMenuTwo = new Menu();
        userMenuTwo.setMenuLink("");
        userMenuTwo.setMenuName("增加用户");
        List<Menu> list = new ArrayList<>();
        list.add(userMenuOne);
        list.add(userMenuTwo);
        userMenu.setMenuVoList(list);

        //创建PS管理Menu
        Menu psMenu = new Menu();
        psMenu.setMenuName("PS台架管理");
        psMenu.setMenuLink("");
        //创建PS管理Menu二级目录
        Menu psMenuOne = new Menu();
        psMenuOne.setMenuLink("");
        psMenuOne.setMenuName("台架管理");
        Menu psMenuTwo = new Menu();
        psMenuTwo.setMenuLink("");
        psMenuTwo.setMenuName("参数管理");
        Menu psMenuThree = new Menu();
        psMenuThree.setMenuLink("");
        psMenuThree.setMenuName("命令信息");
        Menu psMenuFour = new Menu();
        psMenuFour.setMenuLink("");
        psMenuFour.setMenuName("驱动控制信息");
        Menu psMenuFive = new Menu();
        psMenuFive.setMenuLink("");
        psMenuFive.setMenuName("负载控制信息");
        Menu psMenuSix = new Menu();
        psMenuSix.setMenuLink("");
        psMenuSix.setMenuName("温度控制");
        Menu psMenuSeven = new Menu();
        psMenuSeven.setMenuLink("");
        psMenuSeven.setMenuName("档位控制");
        Menu psMenuEight = new Menu();
        psMenuEight.setMenuLink("");
        psMenuEight.setMenuName("故障控制");
        Menu psMenuNine = new Menu();
        psMenuNine.setMenuLink("");
        psMenuNine.setMenuName("设备报警");
        Menu psMenuTen = new Menu();
        psMenuTen.setMenuLink("");
        psMenuTen.setMenuName("变速箱档位表");
        Menu psMenuEleven = new Menu();
        psMenuEleven.setMenuLink("");
        psMenuEleven.setMenuName("设备状态界面");
        Menu psMenuTwelve = new Menu();
        psMenuTwelve.setMenuLink("");
        psMenuTwelve.setMenuName("日志功能表");
        Menu psMenuThirteen = new Menu();
        psMenuThirteen.setMenuLink("");
        psMenuThirteen.setMenuName("曲线图表");
        Menu psMenuFourteen = new Menu();
        psMenuFourteen.setMenuLink("");
        psMenuFourteen.setMenuName("文件管理");
        Menu psMenuFifteen = new Menu();
        psMenuFifteen.setMenuLink("");
        psMenuFifteen.setMenuName("实验数据记录");
        Menu psMenuSixteen = new Menu();
        psMenuSixteen.setMenuLink("");
        psMenuSixteen.setMenuName("文件选择");
        Menu psMenuSeventeen = new Menu();
        psMenuSeventeen.setMenuLink("");
        psMenuSeventeen.setMenuName("数据文件界面");

        List<Menu> pslist = new ArrayList<>();
        pslist.add(psMenuOne);
        pslist.add(psMenuTwo);
        pslist.add(psMenuThree);
        pslist.add(psMenuFour);
        pslist.add(psMenuFive);
        pslist.add(psMenuSix);
        pslist.add(psMenuSeven);
        pslist.add(psMenuEight);
        pslist.add(psMenuNine);
        pslist.add(psMenuTen);
        pslist.add(psMenuEleven);
        pslist.add(psMenuThirteen);
        pslist.add(psMenuFifteen);
        pslist.add(psMenuFourteen);
        pslist.add(psMenuSixteen);
        pslist.add(psMenuSeventeen);
        pslist.add(psMenuTwelve);
        psMenu.setMenuVoList(pslist);

        menuVoList.add(userMenu);
        menuVoList.add(psMenu);

        view.addObject("menuVoList", menuVoList);
//        httpSession.setAttribute("menuVoList",menuVoList);
        view.setViewName("index");
        return view;
    }

    /**
     * 后台系统退出登录
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public ModelAndView userLogout() {
        ModelAndView view = new ModelAndView();

        view.setViewName("../../login");
        return view;
    }

    @RequestMapping(value = "/showUser")
    @ResponseBody
    public ModelAndView showUser() {
        ModelAndView view = new ModelAndView();
        List<User> userList=new ArrayList<>();
        userList=userService.getAllUser();
        Pager pager = new Pager();
        pager.setNow(1);
        pager.setNum(2);
        pager.setList(userList);
        //view.addObject("userList",userList);
        view.addObject("pager",pager);
        view.setViewName("showUser");
        return view;
    }
}
