package com.liang.demo.dao;

import com.liang.demo.domain.PsLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/15.
 */
@Repository
public interface PsLogMapper {
    /**
     * 向PS_TESTRECORD表中插入数据
     * @param psLog
     * @return
     */
    Integer insertPsLog(PsLog psLog);

    /**
     * 获取PS_TESTRECORD表中所有相关数据
     * @return
     */
    List<PsLog> getAllLog();
}
