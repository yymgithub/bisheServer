package com.liang.demo.service.impl;

import com.liang.demo.dao.PsTestRecordMapper;
import com.liang.demo.domain.PsTestRecord;
import com.liang.demo.domain.User;
import com.liang.demo.service.PsTestRecordService;
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
public class PsTestRecordServiceImpl implements PsTestRecordService {
    @Resource
    private PsTestRecordMapper psTestRecordMapper;
    public  static  final Logger logger= LogManager.getLogger(PsTempServiceImpl.class);
    /**
     * 向PS_TESTRECORD表中插入数据
     *
     * @param psTestRecord
     * @return
     */
    @Override
    public boolean insertPsTestRecord(PsTestRecord psTestRecord) {
        if(psTestRecord==null) return false;
        try{
            Integer res=psTestRecordMapper.insertPsTestRecord(psTestRecord);
            if(res==1) return true;
        }catch(Throwable e){
            logger.error("向表PS_TESTRECORD插入数据时Service层出错");
        }
        return false;
    }

    /**
     * 获取PS_TESTRECORD表中所有相关数据
     *
     * @return
     */
    @Override
    public List<PsTestRecord> getAllTestRecord() {
        List<PsTestRecord> psTestRecordList = new ArrayList<>();
        try {
            psTestRecordList = psTestRecordMapper.getAllTestRecord();
            if(psTestRecordList!=null) return psTestRecordList;
        } catch (Error e) {
            logger.error("查看实验记录service层异常", e);
        }
        return null;
    }
}
