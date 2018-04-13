package com.liang.demo.service.impl;

import com.liang.demo.dao.PsLoadMapper;
import com.liang.demo.domain.PsLoad;
import com.liang.demo.service.PsLoadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 永远有多远 on 2018/4/11.
 */
@Service
public class PsLoadServiceImpl implements PsLoadService {
    @Resource
    private PsLoadMapper psLoadMapper;
    public  static final Logger logger= LogManager.getLogger(PsLoadServiceImpl.class);
    /**
     * APP完成手动控制负载电机模块实现向PS_LOAD表记录
     *
     * @param psLoad
     * @return
     */
    @Override
    public boolean insertPsload(PsLoad psLoad) {
        if(psLoad==null) return false;
        try{
            Integer res=psLoadMapper.insertPsload(psLoad);
            if(res==1) return true;
        }catch(Throwable e){
            logger.error("向表PS_LOAD插入数据时Service层出错");
        }

        return false;
    }
}
