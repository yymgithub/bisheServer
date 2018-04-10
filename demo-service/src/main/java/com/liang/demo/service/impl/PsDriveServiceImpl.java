package com.liang.demo.service.impl;

import com.liang.demo.dao.PsDriveMapper;
import com.liang.demo.domain.PsDrive;
import com.liang.demo.service.PsDriveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 永远有多远 on 2018/4/10.
 */
@Service
public class PsDriveServiceImpl implements PsDriveService{
    @Resource
   private PsDriveMapper psDriveMapper;
   public static final Logger logger=LogManager.getLogger(PsDriveServiceImpl.class);

    /**
     * PS_DRVIE插入数据是否成功
     *
     * @param psDrive
     * @return
     */
    @Override
    public boolean insertPsDrive(PsDrive psDrive) {
        if(psDrive==null) return false;
        try{
           if(psDriveMapper.insertPsDrive(psDrive)==1){
               return true;
           }
        }catch (Throwable e){
            logger.error("插入PS_DRIVE表数据时候Service层出错");
        }
        return false;
    }
}
