package com.liang.demo.service;

import com.liang.demo.domain.PsDataFile;

/**
 * Created by 永远有多远 on 2018/4/13.
 */
public interface PsDataFileService{
    /**
     * App新建数据文件用户记录操作
     * @param psDataFile
     * @return
     */
    boolean insertPsDataFile(PsDataFile psDataFile);
}
