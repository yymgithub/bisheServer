package com.liang.demo.web.controller;

import com.liang.demo.dao.PSBenchMapper;
import com.liang.demo.dao.PsDeviceAlarmMapper;
import com.liang.demo.dao.PsParameterMapper;
import com.liang.demo.domain.*;
import com.liang.demo.service.PsBenchService;
import com.liang.demo.service.PsCommandService;
import com.liang.demo.service.PsDriveService;
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
    @Resource
    private PsBenchService psBenchService;
    @Resource
    private PsCommandService psCommandService;
    @Resource
    private PsDeviceAlarmMapper psDeviceAlarmMapper;
    @Resource
    private PSBenchMapper psBenchMapper;
    @Resource
    private PsDriveService psDriveService;
    @Resource
    private PsParameterMapper psParameterMapper;
    public static final Logger logger = LogManager.getLogger(HomeController.class);


    /**
     * 根据台架号获取该台架的所有参数
     *
     * @param psId
     * @return
     */

    @RequestMapping(value = "/appGetPara")
    @ResponseBody
    public Result getParameterByPsId(Integer psId) {
        Result result = new Result();
        List<PsParameter> psParameterList = new ArrayList<>();

        Map<String, Object> map = new HashMap<String, Object>();
        if (psId == null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("未获取到台架号");
            return result;
        }
        try {
            psParameterList = psParameterService.getPsParameterByPsId(psId);
            PsBench psBench = psBenchService.selectPsBenchByPsId(psId);
            if (psParameterList != null) {
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("获取成功");
                map.put("psParameterList", psParameterList);
                map.put("psBench", psBench);
                result.setData(map);
            } else {
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("获取台架参数时失败");
            }

        } catch (Throwable e) {
            logger.error("获取台架参数时控制层异常", e);
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("获取台架参数时Controller层出现异常");
        }
        return result;
    }

    @RequestMapping("/command/stop")
    @ResponseBody
    public Result commandStopBench(Integer psId, String phoneId, String comName) {
        Result result = new Result();
        if (psId == null || phoneId == null || comName == null) {
            result.setSuccess(false);
            result.setCode(3);
            if (comName.equals("停车")) {
                result.setMessage("APP命令控制停车时传入参数失败");
            } else if (comName.equals("关闭变频器")) {
                result.setMessage("APP命令控制关闭变频器时传入参数失败");
            }
            return result;
        }
        try {
            PsCommand psCommand = new PsCommand();
            psCommand.setPsId(psId);
            psCommand.setPhoneId(phoneId);
            psCommand.setComName(comName);
            Integer res1 = psCommandService.insertPsCommand(psCommand);
            boolean res2 = psCommandService.commandStop(psId);
            if (res1 == 1 && res2) {
                result.setSuccess(true);
                result.setCode(1);
                if (comName.equals("停车")) {
                    result.setMessage("停车成功");
                } else if (comName.equals("关闭变频器")) {
                    result.setMessage("关闭变频器成功");
                }
            } else {
                result.setSuccess(false);
                result.setCode(3);
                if (comName.equals("停车")) {
                    result.setMessage("停车失败");
                } else if (comName.equals("关闭变频器")) {
                    result.setMessage("关闭变频器失败");
                }
            }

        } catch (Throwable e) {
            logger.error("APP命令控制停车时控制层异常", e);
            result.setSuccess(false);
            result.setCode(3);
            if (comName.equals("停车")) {
                result.setMessage("停车失败");
            } else if (comName.equals("关闭变频器")) {
                result.setMessage("关闭变频器失败");
            }
        }
        return result;
    }


    @RequestMapping("/command/startUp")
    @ResponseBody
    public Result commandStartUp(Integer psId, String phoneId, String comName) {
        Result result = new Result();
        if (psId == null || phoneId == null || comName == null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("APP命令控制启动变频器时传入参数失败");
            return result;
        }
        try {
            PsCommand psCommand = new PsCommand();
            psCommand.setPsId(psId);
            psCommand.setPhoneId(phoneId);
            psCommand.setComName(comName);
            Integer res1 = psCommandService.insertPsCommand(psCommand);
            boolean res2 = psCommandService.commandStartUp(psId);
            if (res1 == 1 && res2) {
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("启动变频器成功");
            } else {
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("启动变频器失败");
            }

        } catch (Throwable e) {
            logger.error("APP命令控制停车时控制层异常", e);
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("启动变频器失败");
        }
        return result;
    }


    @RequestMapping("/command/alarmUp")
    @ResponseBody
    public Result commandAlarmUp(Integer psId, String phoneId, String comName) {
        Result result = new Result();
        if (psId == null || phoneId == null || comName == null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("APP命令控制报警时传入参数失败");
            return result;
        }
        try {
            PsCommand psCommand = new PsCommand();
            psCommand.setPsId(psId);
            psCommand.setPhoneId(phoneId);
            psCommand.setComName(comName);
            Integer res1 = psCommandService.insertPsCommand(psCommand);
            PsDeviceAlarm psDeviceAlarm = new PsDeviceAlarm();
            psDeviceAlarm.setPsId(psId);
            psDeviceAlarm.setDeState(1);
            Integer res2 = psDeviceAlarmMapper.insertPsDeviceAlarm(psDeviceAlarm);
            PsBench psBench = new PsBench();
            psBench.setPsId(psId);
            psBench.setPsAlarm(1);
            Integer res3 = psBenchMapper.updatePsBenchAlarm(psBench);
            if (res1 == 1 && res2 == 1 && res3 == 1) {
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("设置报警成功");
            } else {
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("设置报警失败");
            }

        } catch (Throwable e) {
            logger.error("APP命令控制报警时控制层异常", e);
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("设置报警失败");
        }
        return result;
    }


    @RequestMapping("/command/alarmOff")
    @ResponseBody
    public Result commandAlarmOff(Integer psId, String phoneId, String comName) {
        Result result = new Result();
        if (psId == null || phoneId == null || comName == null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("APP命令控制关闭报警时传入参数失败");
            return result;
        }
        try {
            PsCommand psCommand = new PsCommand();
            psCommand.setPsId(psId);
            psCommand.setPhoneId(phoneId);
            psCommand.setComName(comName);
            Integer res1 = psCommandService.insertPsCommand(psCommand);
            PsDeviceAlarm psDeviceAlarm = new PsDeviceAlarm();
            psDeviceAlarm.setPsId(psId);
            psDeviceAlarm.setDeState(2);
            Integer res2 = psDeviceAlarmMapper.insertPsDeviceAlarm(psDeviceAlarm);
            PsBench psBench = new PsBench();
            psBench.setPsId(psId);
            psBench.setPsAlarm(0);
            Integer res3 = psBenchMapper.updatePsBenchAlarm(psBench);
            if (res1 == 1 && res2 == 1 && res3 == 1) {
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("设置关闭报警成功");
            } else {
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("设置关闭报警报警失败");
            }

        } catch (Throwable e) {
            logger.error("APP命令控制关闭报警时控制层异常", e);
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("设置关闭报警失败");
        }
        return result;
    }

    @RequestMapping("/manc/drive")
    @ResponseBody
    public Result drive_setting(Integer psId, String phoneId,Integer drMode,double drLoad,Integer drRamptime,Integer drReverse,Integer drRemotestatus){
        Result result=new Result();
        if(psId==null||phoneId==null||drMode==null|drRamptime==null||drRemotestatus==null||drReverse==null){
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("参数传入Controller层异常");
        }
        try{
            PsDrive psDrive=new PsDrive();
            psDrive.setPsId(psId);
            psDrive.setPhoneId(phoneId);
            psDrive.setDrMode(drMode);
            psDrive.setDrLoad(drLoad);
            psDrive.setDrRamptime(drRamptime);
            psDrive.setDrReverse(drReverse);
            psDrive.setDrRemotestatus(drRemotestatus);
            if(psDriveService.insertPsDrive(psDrive)){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("驱动模式",drMode);
                map.put("驱动斜坡时间",drRamptime);
                map.put("驱动转速",drLoad);
                map.put("驱动是否反转",drReverse);
                map.put("驱动是否远程",drRemotestatus);
                List<PsParameter> psParameterList=psParameterMapper.getPsParameterByPsId(psId);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                   for(int i=0;i<psParameterList.size();i++){
                       if(entry.getKey().equals(psParameterList.get(i).getParaName())){
                           psParameterList.get(i).setParaValue(Double.valueOf(String.valueOf(entry.getValue())));
                           Integer res=psParameterMapper.updatePsParameterByParaId(psParameterList.get(i));
                           if(res!=1){
                               result.setSuccess(false);
                               result.setCode(3);
                               result.setMessage("进行驱动电机给定Controller层出错");
                               return result;
                           }

                       }
                   }
                }
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("手动控制驱动电机设置成功");

            }
            else{
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("进行驱动电机给定Controller层出错");
            }
        }catch (Throwable e){
            logger.error("进行驱动电机给定Controller层出错");
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("进行驱动电机给定Controller层出错");

        }
        return result;

    }


}
