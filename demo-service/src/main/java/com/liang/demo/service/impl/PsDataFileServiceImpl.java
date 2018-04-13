package com.liang.demo.service.impl;

import com.liang.demo.dao.PsDataFileMapper;
import com.liang.demo.dao.PsGearMapper;
import com.liang.demo.domain.PsDataFile;
import com.liang.demo.domain.PsDrive;
import com.liang.demo.service.PsDataFileService;
import com.liang.demo.service.PsDriveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 永远有多远 on 2018/4/13.
 */
@Service
public class PsDataFileServiceImpl implements PsDataFileService{
    @Resource
    private PsDataFileMapper psDataFileMapper;
    public  static final Logger logger= LogManager.getLogger(PsGearServiceImpl.class);
    /**
     * App新建数据文件用户记录操作
     *
     * @param psDataFile
     * @return
     */
    @Override
    public boolean insertPsDataFile(PsDataFile psDataFile) {
        if(psDataFile==null) return false;
        try{
            Integer res=psDataFileMapper.insertPsDatFile(psDataFile);
            if(res==1) return true;
        }catch(Throwable e){
            logger.error("向表PS_DATAFILE插入数据时Service层出错");
        }

        return false;
    }
}
