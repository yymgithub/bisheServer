package com.liang.demo.service;

import com.liang.demo.domain.PsDeviceAlarm;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/3.
 */
public interface PsDeviceAlarmService {
    /**
     *APP命令控制点击报警或者解除报警时向表中插入数据
     * @param psDeviceAlarm
     * @return
     */
    Integer insertPsDeviceAlarm(PsDeviceAlarm psDeviceAlarm);

    /**
     * 获得所有报警设备
     * @return
     */
    List<PsDeviceAlarm> getAllDeviceAlarm();
}
