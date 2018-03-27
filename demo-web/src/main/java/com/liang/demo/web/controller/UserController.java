package com.liang.demo.web.controller;

import com.liang.demo.domain.Menu;
import com.liang.demo.domain.Page;
import com.liang.demo.domain.Result;
import com.liang.demo.domain.User;
import com.liang.demo.service.UserService;
import com.liang.demo.util.BaseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
        userMenuTwo.setMenuLink("/user/addUser");
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
        view.setViewName("/index");
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
    public ModelAndView showUser(@ModelAttribute("page") Page page) {
        ModelAndView view = new ModelAndView();
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUser();
        Page pageP = new Page();
        //pageP.setDatas(userList);
        //设置每页多少条
        pageP.setItemsPerPage(1);
        pageP.setItems(userList.size());
        if (page.getPage() == null) {
            page.setPage(1);
        }
        pageP.setPage(page.getPage());
        List<User> perList = new ArrayList<>();
        int num = 0;
        int a = pageP.getItemsPerPage() * pageP.getPage();
        int b = a - pageP.getItemsPerPage() + 1;
        for (User user : userList) {
            num++;
            if (num >= b && num <= a) {
                perList.add(user);
            }
        }
        pageP.setDatas(perList);

        //view.addObject("userList",userList);
        view.addObject("page", pageP);
        view.setViewName("/showUser");
        return view;
    }

    @RequestMapping(value = "/addUser")
    @ResponseBody
    public ModelAndView addUser() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/jspyy/addUser");
        return view;
    }

    @RequestMapping(value = "/resetAddUser")
    @ResponseBody
    public ModelAndView resetAddUser() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/jspyy/addUser");
        return view;
    }

    @RequestMapping(value = "/addUserSave")
    @ResponseBody
    public Result addUserSave(@ModelAttribute("user") User user) {
        Result result = new Result();
        //BaseUtil.isNullOrEmpty相当于 == null || .equals("")
        if (BaseUtil.isNullOrEmpty(user.getPhoneId()) || BaseUtil.isNullOrEmpty(user.getPassword()) || user.getUserRole() == null || user.getUserRole() > 3 || user.getUserRole() < 1) {
            result.setSuccess(false);
            result.setCode(100);
            result.setMessage("输入参数错误，请重新输入");
            return result;
        }
        try {
            Integer suc = userService.createdUser(new User(user.getPhoneId(), user.getUserName(), user.getUserRole(), user.getPassword()));
            if (suc == 1) {
                result.setSuccess(true);
                result.setCode(200);
                result.setMessage("创建成功");
            } else {
                result.setSuccess(false);
                result.setCode(100);
                result.setMessage("创建失败");
            }
        } catch (Error error) {
            logger.error("创建用户的Controller层出现异常", error);
            result.setSuccess(false);
            result.setCode(100);
            result.setMessage("创建用户的Controller层出现异常");
        }
        return result;
    }

    @RequestMapping(value = "/editUser")
    @ResponseBody
    public ModelAndView editUser(@ModelAttribute("id") Integer id) {
        //@ModelAttribute("id") Integer id
        ModelAndView view = new ModelAndView();
        User us =userService.getUserById(id);
        view.addObject("userU",us);
        view.setViewName("/jspyy/editUser");
        //view.addObject("user", userService.userLogin(new User("15116100440", "123456")));
        return view;
    }
    //修改用户信息之后保存
    @RequestMapping(value = "/editUserSave")
    @ResponseBody
    public Result editUserSave(String phoneId ,String userName,String password ,Integer userRole,Integer yn) {

        Result result = new Result();
            User user=userService.getUserByPhoneId(phoneId);
            user.setUserRole(userRole);
            user.setUserName(userName);
            user.setYn(yn);
            user.setPassword(password);
//        BaseUtil.isNullOrEmpty相当于 == null || .equals("")
        if (BaseUtil.isNullOrEmpty(phoneId) || BaseUtil.isNullOrEmpty(password) || userRole == null || userRole > 3 || userRole < 1) {
            result.setSuccess(false);
            result.setCode(100);
            result.setMessage("输入参数错误，请重新输入");
            return result;
        }
        try {
            boolean suc = userService.updateUser(user);
            if (suc) {
                result.setSuccess(true);
                result.setCode(200);
                result.setMessage("修改成功");
            } else {
                result.setSuccess(false);
                result.setCode(100);
                result.setMessage("修改失败");
            }
        } catch (Error error) {
            logger.error("创建用户的Controller层出现异常", error);
            result.setSuccess(false);
            result.setCode(100);
            result.setMessage("创建用户的Controller层出现异常");
        }
        return result;
    }

    //停用或者启用现有用户
    @RequestMapping(value = "/disableUser")
    @ResponseBody
    public Result disableUser(@ModelAttribute("id") Integer id,@ModelAttribute("yn") Integer yn) {
        User us = userService.getUserById(id);
        Result result = new Result();
        if (id == null) {
            result.setSuccess(false);
            result.setCode(100);
            result.setMessage("操作失败");
        }
        if (us.getYn() == 1) {
            us.setYn(0);
        } else {
            us.setYn(1);
        }
        try {
            boolean suc = userService.updateUserYnById(us);
            if (suc) {
                result.setSuccess(true);
                result.setCode(200);
                result.setMessage("操作成功");
            } else {
                result.setSuccess(false);
                result.setCode(100);
                result.setMessage("操作失败");
            }
        } catch (Error error) {
            logger.error("停用或启用用户的Controller层出现异常", error);
            result.setSuccess(false);
            result.setCode(100);
            result.setMessage("停用或启用用户的Controller层出现异常");
        }

        return result;
    }

    @RequestMapping(value = "/resetEditUser")
    @ResponseBody
    public ModelAndView resetEditUser(@ModelAttribute("id") Integer id) {
        ModelAndView view = new ModelAndView();
        User us =userService.getUserById(id);
        view.addObject("userU",us);
        view.setViewName("/jspyy/editUser");
        return view;
    }
}
