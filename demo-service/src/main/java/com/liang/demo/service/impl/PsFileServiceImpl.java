package com.liang.demo.service.impl;

import com.liang.demo.dao.PsFileMapper;
import com.liang.demo.dao.PsGearMapper;
import com.liang.demo.domain.PsFile;
import com.liang.demo.service.PsDriveService;
import com.liang.demo.service.PsFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/13.
 */
@Service
public class PsFileServiceImpl implements PsFileService {
    @Resource
    private PsFileMapper psFileMapper;
    public  static final Logger logger= LogManager.getLogger(PsGearServiceImpl.class);
    /**
     * App新建数据文件向文件管理表中插入新文件
     *
     * @param psFile
     * @return
     */
    @Override
    public boolean insertPsFile(PsFile psFile) {
        if(psFile==null) return false;
        try{
            Integer res=psFileMapper.insertPsFile(psFile);
            if(res==1) return true;
        }catch(Throwable e){
            logger.error("向表PS_FILE插入数据时Service层出错");
        }

        return false;
    }

    /**
     * 文件选择通过台架号和文件状态选择文件
     *
     * @param psId
     * @return
     */
    @Override
    public List<PsFile> getPsFileByPsIdAndState(Integer psId) {
        if (psId == null) return null;
        try {
            List<PsFile> psFileList =psFileMapper.getPsFileByPsIdAndState(psId);
           return psFileList;
        } catch (Throwable e) {
            logger.error("选择文件时service层异常", e);
        }
        return null;
    }

    /**
     * 根据文件执行状态更新文件状态
     *
     * @param psFile
     * @return
     */
    @Override
    public boolean updatePsFileFileStateByFileId(PsFile psFile) {
        if(psFile==null) return false;
        try{
            if(psFileMapper.updatePsFileFileStateByFileId(psFile)==1)
                return true;
        }catch(Throwable e){
            logger.error("更新文件状态时service层异常", e);
        }
        return false;
    }
}
