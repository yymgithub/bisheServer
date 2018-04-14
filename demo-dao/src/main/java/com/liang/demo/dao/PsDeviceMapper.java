package com.liang.demo.dao;

import com.liang.demo.domain.PsDevice;
import org.apache.ibatis.annotations.Param;
import org.springframework.asm.Label;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/14.
 */
@Repository
public interface PsDeviceMapper {
    /**
     * 更新设备状态的状态、连接时间和连接数
     * @param psDevice
     * @return
     */
    Integer updatePsDevice(PsDevice psDevice);

    /**
     * 根据台架号获得相应的设备信息
     * @param psId
     * @return
     */
    List<PsDevice> selectDevice(@Param("psId") Integer psId);

    /**
     * 获取所有设备状态
     * @return
     */
    List<PsDevice> selectDeviceList();
}
