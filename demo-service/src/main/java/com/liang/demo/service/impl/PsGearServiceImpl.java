package com.liang.demo.service.impl;

import com.liang.demo.dao.PsGearMapper;
import com.liang.demo.dao.PsLoadMapper;
import com.liang.demo.domain.PsGear;
import com.liang.demo.service.PsGearService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 永远有多远 on 2018/4/12.
 */
@Service
public class PsGearServiceImpl implements PsGearService {
    @Resource
    private PsGearMapper psGearMapper;
    public  static final Logger logger= LogManager.getLogger(PsGearServiceImpl.class);
    /**
     * APP档位控制换挡操作记录
     *
     * @param psGear
     * @return
     */
    @Override
    public boolean insertPsGear(PsGear psGear) {
        if(psGear==null) return false;
        try{
            Integer res=psGearMapper.insertPsGear(psGear);
            if(res==1) return true;
        }catch(Throwable e){
            logger.error("向表PS_GEAR插入数据时Service层出错");
        }

        return false;
    }
}
