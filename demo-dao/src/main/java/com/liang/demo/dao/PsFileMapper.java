package com.liang.demo.dao;

import com.liang.demo.domain.PsFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/13.
 */
@Repository
public interface PsFileMapper {
    /**
     * 创建新数据文件时，不但记录还向管理文件表插入新的文件信息
     * @param psFile
     * @return
     */
    Integer insertPsFile(PsFile psFile);

    /**
     * 文件选择通过台架号和文件状态选择文件
     * @param psId
     * @return
     */
    List<PsFile> getPsFileByPsIdAndState(@Param("psId") Integer psId);
}
