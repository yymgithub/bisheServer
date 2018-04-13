package com.liang.demo.web.controller;

import com.liang.demo.dao.PSBenchMapper;
import com.liang.demo.dao.PsDeviceAlarmMapper;
import com.liang.demo.dao.PsParameterMapper;
import com.liang.demo.domain.*;
import com.liang.demo.service.*;
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
    @Resource
    private PsLoadService psLoadService;
    @Resource
    private PsTempService psTempService;
    @Resource
    private PsFaultService psFaultService;
    @Resource
    private PsGearService psGearService;
    @Resource
    private PsDataFileService psDataFileService;
    @Resource
    private PsFileService psFileService;
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
@RequestMapping("/manc/load")
@ResponseBody
    public Result load_setting(Integer psId, String phoneId,Integer loMode,Integer loRamptime,double lo1Speed,Integer lo1Reverse,Integer lo1Remote,double lo2Speed,Integer lo2Reverse,Integer lo2Remote){
        Result result=new Result();
        if(psId==null||phoneId==null||loMode==null|loRamptime==null||lo1Reverse==null||lo1Remote==null||lo2Reverse==null||lo2Remote==null){
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("参数传入Controller层异常");
        }
        try{
            PsLoad psLoad=new PsLoad();
            psLoad.setPsId(psId);
            psLoad.setPhoneId(phoneId);
            psLoad.setLoMode(loMode);
            psLoad.setLoRamptime(loRamptime);
            psLoad.setLo1Speed(lo1Speed);
            psLoad.setLo1Reverse(lo1Reverse);
            psLoad.setLo1Remote(lo1Remote);
            psLoad.setLo2Speed(lo2Speed);
            psLoad.setLo2Reverse(lo2Reverse);
            psLoad.setLo2Remote(lo2Remote);
            if(psLoadService.insertPsload(psLoad)){
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("负载模式",loMode);
                map.put("负载斜坡时间",loRamptime);
                map.put("负载1转速",lo1Speed);
                map.put("负载1是否反转",lo1Reverse);
                map.put("负载1是否远程",lo1Remote);
                map.put("负载2转速",lo2Speed);
                map.put("负载2是否反转",lo2Reverse);
                map.put("负载2是否远程",lo2Remote);
                List<PsParameter> psParameterList=psParameterMapper.getPsParameterByPsId(psId);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    for(int i=0;i<psParameterList.size();i++){
                        if(entry.getKey().equals(psParameterList.get(i).getParaName())){
                            psParameterList.get(i).setParaValue(Double.valueOf(String.valueOf(entry.getValue())));
                            Integer res=psParameterMapper.updatePsParameterByParaId(psParameterList.get(i));
                            if(res!=1){
                                result.setSuccess(false);
                                result.setCode(3);
                                result.setMessage("进行负载电机给定Controller层出错");
                                return result;
                            }

                        }
                    }
                }
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("手动控制负载电机设置成功");

            }
            else{
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("进行负载电机给定Controller层出错");
            }
        }catch(Throwable e){
            logger.error("进行负载电机给定Controller层出错");
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("进行负载电机给定Controller层出错");
        }


        return result;
    }
@RequestMapping("/manc/getTemp")
@ResponseBody
  public Result getNowTemp(Integer psId){
      Result result=new Result();
      if(psId==null) {
          result.setSuccess(false);
          result.setCode(3);
          result.setMessage("参数传入Controller层异常");
      }
      try{
          Map<String, Object> map = new HashMap<String, Object>();
          List<PsParameter> psParameterList=psParameterMapper.getPsParameterByPsId(psId);
          List<PsParameter> getNowTempList=new ArrayList<>();
          for(int i=0;i<psParameterList.size();i++){
              if(psParameterList.get(i).getParaName().equals("油温")||psParameterList.get(i).getParaName().equals("变速箱温度")){
                  getNowTempList.add(psParameterList.get(i));
              }
          }
          result.setSuccess(true);
          result.setCode(1);
          result.setMessage("手动控制负载电机设置成功");
          map.put("getNowTempList",getNowTempList);
          result.setData(map);

      }catch (Throwable e){
          logger.error("获取当前温度时Controller层出错");
          result.setSuccess(false);
          result.setCode(3);
          result.setMessage("获取当前温度时Controller层出错");
      }
      return result;

  }

    @RequestMapping("/manc/setTemp")
    @ResponseBody
    public Result setTemp(Integer psId,double paraValue,String phoneId){
        Result result=new Result();
        if(psId==null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("参数传入Controller层异常");
        }
        try{
            PsTemp psTemp=new PsTemp();
            psTemp.setPhoneId(phoneId);
            psTemp.setPsId(psId);
            psTemp.setTeName("变速箱目标温度");
            psTemp.setTeValue(paraValue);
            if(psTempService.insertPsTemp(psTemp)){
                List<PsParameter> psParameterList=psParameterMapper.getPsParameterByPsId(psId);
                for(int i=0;i<psParameterList.size();i++){
                    if(psParameterList.get(i).getParaName().equals("变速箱温度")){
                        psParameterList.get(i).setParaValue(paraValue);
                        Integer res=psParameterMapper.updatePsParameterByParaId( psParameterList.get(i));
                        if(res!=1){
                            result.setSuccess(false);
                            result.setCode(3);
                            result.setMessage("设置变速箱温度时Controller层出错");
                        }
                    }
                }
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("手动控制温度控制变速箱温度设置成功");
            }
            else{
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("设置变速箱温度时Controller层出错");
            }


        }catch (Throwable e){
            logger.error("设置变速箱温度时Controller层出错");
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("设置变速箱温度时Controller层出错");
        }
        return result;

    }

    @RequestMapping("/manc/setTemp1")
    @ResponseBody
    public Result setTemp1(Integer psId,double paraValue,String phoneId){
        Result result=new Result();
        if(psId==null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("参数传入Controller层异常");
        }
        try{
            PsTemp psTemp=new PsTemp();
            psTemp.setPhoneId(phoneId);
            psTemp.setPsId(psId);
            psTemp.setTeName("油温");
            psTemp.setTeValue(paraValue);
            if(psTempService.insertPsTemp(psTemp)) {
                List<PsParameter> psParameterList = psParameterMapper.getPsParameterByPsId(psId);
                for (int i = 0; i < psParameterList.size(); i++) {
                    if (psParameterList.get(i).getParaName().equals("油温")) {
                        psParameterList.get(i).setParaValue(paraValue);
                        Integer res = psParameterMapper.updatePsParameterByParaId(psParameterList.get(i));
                        if (res != 1) {
                            result.setSuccess(false);
                            result.setCode(3);
                            result.setMessage("设置油温时Controller层出错");
                        }
                    }
                }
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("手动控制温度控制油温设置成功");
            }
            else{
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("设置油温时Controller层出错");
            }
        }catch (Throwable e){
            logger.error("设置油温时Controller层出错");
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("设置油温时Controller层出错");
        }
        return result;

    }


    @RequestMapping("/manc/getGear")
    @ResponseBody
    public Result getNowGear(Integer psId){
        Result result=new Result();
        if(psId==null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("参数传入Controller层异常");
        }
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            List<PsParameter> psParameterList=psParameterMapper.getPsParameterByPsId(psId);
            List<PsParameter> getNowGearList=new ArrayList<>();
            for(int i=0;i<psParameterList.size();i++){
                if(psParameterList.get(i).getParaName().equals("故障状态")||psParameterList.get(i).getParaName().equals("当前档位")){
                    getNowGearList.add(psParameterList.get(i));
                }
            }
            result.setSuccess(true);
            result.setCode(1);
            result.setMessage("手动控制档位初始数据获取成功成功");
            map.put("getNowGearList",getNowGearList);
            result.setData(map);

        }catch (Throwable e){
            logger.error("获取当前档位时Controller层出错");
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("获取当前档位时Controller层出错");
        }
        return result;

    }

    @RequestMapping("/manc/setGear")
    @ResponseBody
    public Result setGear(Integer psId,String gearName,String phoneId){
        Result result=new Result();
        if(psId==null||gearName==null||phoneId==null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("参数传入Controller层异常");
        }
        try{
           PsGear psGear=new PsGear();
            psGear.setPsId(psId);
            psGear.setGearName(gearName);
            psGear.setPhoneId(phoneId);
            if(psGearService.insertPsGear(psGear)){
                List<PsParameter> psParameterList=psParameterMapper.getPsParameterByPsId(psId);
                for(int i=0;i<psParameterList.size();i++){
                    if(psParameterList.get(i).getParaName().equals("当前档位")){
                        Integer change=0;
                        if(gearName.equals("1档")){
                            change=0;
                        }
                        else if(gearName.equals("2档")){
                            change=1;
                        }
                        else if(gearName.equals("3档")){
                            change=2;
                        }
                        else if(gearName.equals("4档")){
                            change=3;
                        }
                        else if(gearName.equals("5档")){
                            change=4;
                        }
                        else if(gearName.equals("6档")){
                            change=5;
                        }
                        psParameterList.get(i).setParaValue(change);
                        Integer res=psParameterMapper.updatePsParameterByParaId( psParameterList.get(i));
                        if(res!=1){
                            result.setSuccess(false);
                            result.setCode(3);
                            result.setMessage("设置档位变化时Controller层出错");
                        }
                    }
                }
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("设置档位变化设置成功");
            }
            else{
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("设置档位变化时Controller层出错");
            }


        }catch (Throwable e){
            logger.error("设置档位变化时Controller层出错");
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("设置档位变化时Controller层出错");
        }
        return result;

    }

    @RequestMapping("/manc/setFault")
    @ResponseBody
    public Result setFault(Integer psId,String faultName,String phoneId){
        Result result=new Result();
        if(psId==null||faultName==null||phoneId==null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("参数传入Controller层异常");
        }
        try{
            PsFault psFault=new PsFault();
            psFault.setPhoneId(phoneId);
            psFault.setPsId(psId);
            psFault.setFaultName(faultName);
            if(psFaultService.insertPsFault(psFault)){
                List<PsParameter> psParameterList=psParameterMapper.getPsParameterByPsId(psId);
                for(int i=0;i<psParameterList.size();i++){
                    if(psParameterList.get(i).getParaName().equals("故障状态")){
                        Integer change=0;
                        if(faultName.equals("变为故障")){
                            change=1;
                        }
                        else if(faultName.equals("变为非故障")){
                            change=0;
                        }
                        psParameterList.get(i).setParaValue(change);
                        Integer res=psParameterMapper.updatePsParameterByParaId( psParameterList.get(i));
                        if(res!=1){
                            result.setSuccess(false);
                            result.setCode(3);
                            result.setMessage("故障变化时Controller层出错");
                        }
                    }
                }
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("故障变化设置成功");
            }
            else{
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("故障变化时Controller层出错");
            }


        }catch (Throwable e){
            logger.error("故障变化时Controller层出错");
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("故障变化时Controller层出错");
        }
        return result;

    }
    @RequestMapping("/program/setFile")
    @ResponseBody
    public Result setFile(Integer psId,String phoneId,String daTestSubject,String daDataDocu,String testType,Integer testNum,String daNote,String testStaff){
        Result result=new Result();
        if(psId==null||daTestSubject==null||phoneId==null||daDataDocu==null||testType==null||testNum==null||daNote==null||testStaff==null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("参数传入Controller层异常");
        }
        try{
           PsFile psFile=new PsFile();
            psFile.setFileName(daDataDocu);
            psFile.setPsId(psId);
            psFile.setFileTestType(daTestSubject);
           PsDataFile psDataFile=new PsDataFile();
            psDataFile.setPsId(psId);
            psDataFile.setPhoneId(phoneId);
            psDataFile.setDaTestSubject(daTestSubject);
            psDataFile.setDaDataDocu(daDataDocu);
            psDataFile.setDaNote(daNote);
            psDataFile.setTestNum(testNum);
            psDataFile.setTestStaff(testStaff);
            psDataFile.setTestType(testType);
            if(psFileService.insertPsFile(psFile)==true&&psDataFileService.insertPsDataFile(psDataFile)==true){
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("新建数据文件设置成功");
            }
            else{
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("新建数据文件时Controller层出错");
            }


        }catch (Throwable e){
            logger.error("新建数据文件时Controller层出错");
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("新建数据文件时Controller层出错");
        }
        return result;

    }


    @RequestMapping("/program/getFile")
    @ResponseBody
    public Result getFile(Integer psId){
        Result result=new Result();
        if(psId==null) {
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("参数传入Controller层异常");
        }
        try{
            List<PsFile> psFileList=psFileService.getPsFileByPsIdAndState(psId);
            if(psFileList==null){
                result.setSuccess(false);
                result.setCode(3);
                result.setMessage("选择文件初始数据时Controller层出错");
            }
            else{
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("psFileList",psFileList);
                result.setData(map);
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("选择文件初始数据设置成功");
            }
        }catch (Throwable e){
            logger.error("选择文件初始数据时Controller层出错");
            result.setSuccess(false);
            result.setCode(3);
            result.setMessage("选择文件初始数据时Controller层出错");
        }
        return result;

    }



}
