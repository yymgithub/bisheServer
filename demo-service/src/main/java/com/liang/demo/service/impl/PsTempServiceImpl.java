package com.liang.demo.service.impl;

import com.liang.demo.dao.PsTempMapper;
import com.liang.demo.domain.PsTemp;
import com.liang.demo.service.PsTempService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 永远有多远 on 2018/4/11.
 */
@Service
public class PsTempServiceImpl implements PsTempService {
    @Resource
    private PsTempMapper psTempMapper;
    public  static  final Logger logger= LogManager.getLogger(PsTempServiceImpl.class);
    @Override
    public boolean insertPsTemp(PsTemp psTemp) {
        if(psTemp==null) return false;
        try{
            Integer res=psTempMapper.insertPsTemp(psTemp);
            if(res==1) return true;
        }catch(Throwable e){
            logger.error("向表PS_TEMP插入数据时Service层出错");
        }
        return false;
    }
}
