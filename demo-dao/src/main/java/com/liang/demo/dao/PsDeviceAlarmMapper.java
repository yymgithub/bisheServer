package com.liang.demo.dao;

import com.liang.demo.domain.PsDeviceAlarm;
import org.springframework.stereotype.Repository;

/**
 * Created by 永远有多远 on 2018/4/3.
 */
@Repository
public interface PsDeviceAlarmMapper {
    /**
     * APP命令控制点击设备报警或者解除报警时想设备报警表插入数据用于记录
     * @param psDeviceAlarm
     * @return
     */
    Integer insertPsDeviceAlarm(PsDeviceAlarm psDeviceAlarm);
}
