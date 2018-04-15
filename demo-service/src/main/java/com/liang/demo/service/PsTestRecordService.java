package com.liang.demo.service;

import com.liang.demo.domain.PsTestRecord;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/15.
 */
public interface PsTestRecordService {
    /**
     * App操作记录试验数据时候插入数据库操作
     * @param psTestRecord
     * @return
     */
    boolean insertPsTestRecord(PsTestRecord psTestRecord);

    /**
     * 查看试验数据时获取所有试验记录数据
     * @return
     */
    List<PsTestRecord> getAllTestRecord();
}
