package com.liang.demo.dao;

import com.liang.demo.domain.PsLoad;
import org.springframework.stereotype.Repository;

/**
 * Created by 永远有多远 on 2018/4/11.
 */
@Repository
public interface PsLoadMapper {
    /**
     * 向PS_LOAD表插入一条新数据
     * @return
     */
    public  Integer insertPsload(PsLoad psLoad);

}
