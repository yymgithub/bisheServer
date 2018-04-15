package com.liang.demo.dao;

import com.liang.demo.domain.PsTestRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/15.
 */
@Repository
public interface PsTestRecordMapper {
    /**
     * 向PS_TESTRECORD表中插入数据
     * @param psTestRecord
     * @return
     */
    Integer insertPsTestRecord(PsTestRecord psTestRecord);

    /**
     * 获取PS_TESTRECORD表中所有相关数据
     * @return
     */
    List<PsTestRecord> getAllTestRecord();

}
