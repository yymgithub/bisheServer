package com.liang.demo.service;

import com.liang.demo.domain.PsLog;
import com.liang.demo.domain.PsTestRecord;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/15.
 */
public interface PsLogService {
    /**
     * 系统运行时有错时插入数据库操作
     * @param psLog
     * @return
     */
    boolean insertPsLog(PsLog psLog);

    /**
     * 查看系统日志时获取所有试验记录数据
     * @return
     */
    List<PsLog> getAllLog();
}
