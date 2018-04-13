package com.liang.demo.dao;

import com.liang.demo.domain.PsTemp;
import org.springframework.stereotype.Repository;

/**
 * Created by 永远有多远 on 2018/4/11.
 */
@Repository
public interface PsTempMapper {
    /**
     * 向PS_TEMP表中插入数据
     * @param psTemp
     * @return
     */
    Integer insertPsTemp(PsTemp psTemp);
}
