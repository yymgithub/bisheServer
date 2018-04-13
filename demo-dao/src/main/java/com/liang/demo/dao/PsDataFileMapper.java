package com.liang.demo.dao;

import com.liang.demo.domain.PsDataFile;
import org.springframework.stereotype.Repository;

/**
 * Created by 永远有多远 on 2018/4/13.
 */
@Repository
public interface PsDataFileMapper {
    /**
     * 创建文件时插入PS_DATA_FILE表一条记录数据
     * @param psDataFile
     * @return
     */
    Integer insertPsDatFile(PsDataFile psDataFile);
}
