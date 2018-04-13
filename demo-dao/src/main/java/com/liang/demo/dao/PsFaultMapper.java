package com.liang.demo.dao;

import com.liang.demo.domain.PsFault;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Repository;

/**
 * Created by 永远有多远 on 2018/4/12.
 */
@Repository
public interface PsFaultMapper {
    /**
     * 向PS_FAULT表中插入数据
     * @param psFault
     * @return
     */
    Integer insertPsFault(PsFault psFault);
}
