package com.liang.demo.service.impl;

import com.liang.demo.dao.PsDeviceAlarmMapper;
import com.liang.demo.domain.PsDeviceAlarm;
import com.liang.demo.service.PsDeviceAlarmService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 永远有多远 on 2018/4/3.
 */
@Service
public class PsDeviceAlarmServiceImpl implements PsDeviceAlarmService {
    private static final Logger logger = LogManager.getLogger(PsDeviceAlarmServiceImpl.class);

    @Resource
    private PsDeviceAlarmMapper psDeviceAlarmMapper;
    /**
     * APP命令控制点击报警或者解除报警时向表中插入数据
     *
     * @param psDeviceAlarm
     * @return
     */
    @Override
    public Integer insertPsDeviceAlarm(PsDeviceAlarm psDeviceAlarm) {
        if(psDeviceAlarm==null) return  0;
        Integer result=0;
        try{
            result=psDeviceAlarmMapper.insertPsDeviceAlarm(psDeviceAlarm);

        }catch (Throwable e){
            logger.error("向设备报警表插入数据时Service层报错",e);
        }
        return null;
    }
}
