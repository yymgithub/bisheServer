package com.liang.demo.web.controller;

import com.liang.demo.domain.PsParameter;
import com.liang.demo.domain.Result;
import com.liang.demo.service.PsParameterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 永远有多远 on 2018/4/1.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    @Resource
    private PsParameterService psParameterService;
    public static final Logger logger = LogManager.getLogger(HomeController.class);


    @RequestMapping(value = "/appGetPara")
    @ResponseBody
    public Result getParameterByPsId(Integer psId){
        Result result=new Result();
        List<PsParameter> psParameterList=new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        if(psId==null){
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("未获取到台架号");
            return result;
        }
        try{
            psParameterList=psParameterService.getPsParameterByPsId(psId);
            if(psParameterList!=null){
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("获取成功");
                map.put("psParameterList",psParameterList);
                result.setData(map);
            }
            else{
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("获取台架参数时失败");
            }

        }catch (Throwable e){
            logger.error("获取台架参数时控制层异常",e);
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("获取台架参数时Controller层出现异常");
        }
        return  result;
    }

}
