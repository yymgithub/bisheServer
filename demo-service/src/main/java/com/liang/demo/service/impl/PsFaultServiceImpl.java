package com.liang.demo.service.impl;

import com.liang.demo.dao.PsFaultMapper;
import com.liang.demo.dao.PsLoadMapper;
import com.liang.demo.domain.PsFault;
import com.liang.demo.service.PsFaultService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 永远有多远 on 2018/4/12.
 */
@Service
public class PsFaultServiceImpl implements PsFaultService {
    @Resource
    private PsFaultMapper psFaultMapper;
    public  static final Logger logger= LogManager.getLogger(PsFaultServiceImpl.class);
    /**
     * App实现故障控制操作记录
     *
     * @param psFault
     * @return
     */
    @Override
    public boolean insertPsFault(PsFault psFault) {
        if(psFault==null) return false;
        try{
            Integer res=psFaultMapper.insertPsFault(psFault);
            if(res==1) return true;
        }catch(Throwable e){
            logger.error("向表PS_Fault插入数据时Service层出错");
        }

        return false;
    }
}
