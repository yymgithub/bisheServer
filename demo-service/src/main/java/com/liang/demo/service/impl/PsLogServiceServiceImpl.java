package com.liang.demo.service.impl;

import com.liang.demo.dao.PsLogMapper;
import com.liang.demo.domain.PsLog;
import com.liang.demo.domain.User;
import com.liang.demo.service.PsLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/15.
 */
@Service
public class PsLogServiceServiceImpl implements PsLogService {
    @Resource
    private PsLogMapper psLogMapper;
    public  static  final Logger logger= LogManager.getLogger(PsTempServiceImpl.class);
    /**
     * 向PS_LOG表中插入数据
     *
     * @param psLog
     * @return
     */
    @Override
    public boolean insertPsLog(PsLog psLog) {
        if(psLog==null) return false;
        try{
            Integer res=psLogMapper.insertPsLog(psLog);
            if(res==1) return true;
        }catch(Throwable e){
            logger.error("向表PS_LOG插入数据时Service层出错");
        }
        return false;
    }

    /**
     * 获取PS_LOG表中所有相关数据
     *
     * @return
     */
    @Override
    public List<PsLog> getAllLog() {
        List<PsLog> psLogList = new ArrayList<>();
        try {
            psLogList =psLogMapper.getAllLog();
            if(psLogList!=null)  return psLogList;

        } catch (Error e) {
            logger.error("查看系统日志service层异常", e);
        }
        return null;
    }
}
