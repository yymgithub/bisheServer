package com.liang.demo.service.impl;

import com.liang.demo.dao.PsDeviceMapper;
import com.liang.demo.domain.PsDevice;
import com.liang.demo.service.PsDeviceService;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/14.
 */
@Service
public class PsDeviceServiceImpl implements PsDeviceService {
    @Resource
    private PsDeviceMapper psDeviceMapper;
    public  static final Logger logger= LogManager.getLogger(PsDeviceServiceImpl.class);
    /**
     * 更新设备状态的状态、连接时间和连接数
     *
     * @param psDevice
     * @return
     */
    @Override
    public boolean updatePsDevice(PsDevice psDevice) {
        if(psDevice==null) return false;
        try{
            if(psDeviceMapper.updatePsDevice(psDevice)==1)
                return true;
        }catch(Throwable e){
            logger.error("更新设备状态时service层异常", e);
        }
        return false;
    }

    /**
     * 根据台架号获得相应的设备信息
     *
     * @param psId
     * @return
     */
    @Override
    public PsDevice selectDevice(@Param("psId") Integer psId) {
        if (psId == null) return null;
        try {
            List<PsDevice> psDevices = psDeviceMapper.selectDevice(psId);
            if (psDevices == null || psDevices.size() != 1) return null;
            else return psDevices.get(0);
        } catch (Throwable e) {
            logger.error("更护台架号获取相应设备时service层异常", e);
        }
        return null;
    }

    /**
     * 获取所有设备状态
     *
     * @return
     */
    @Override
    public List<PsDevice> selectDeviceList() {
        List<PsDevice> psDeviceList = new ArrayList<>();
        try {
            psDeviceList = psDeviceMapper.selectDeviceList();
            return psDeviceList;
        } catch (Error e) {
            logger.error("获取所有设备状态时service层异常", e);
        }
        return null;
    }
}
