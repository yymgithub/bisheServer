package com.liang.demo.service;

import com.liang.demo.domain.PsFile;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/13.
 */
public interface PsFileService {
    /**
     * App新建数据文件向文件管理表中插入新文件
     * @param psFile
     * @return
     */
    boolean insertPsFile(PsFile psFile);

    /**
     * 文件选择通过台架号和文件状态选择文件
     * @param psId
     * @return
     */
    List<PsFile> getPsFileByPsIdAndState(Integer psId);

}
