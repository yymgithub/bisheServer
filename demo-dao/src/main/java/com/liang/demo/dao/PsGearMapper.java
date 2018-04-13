package com.liang.demo.dao;

import com.liang.demo.domain.PsGear;
import org.springframework.stereotype.Repository;

/**
 * Created by 永远有多远 on 2018/4/12.
 */
@Repository
public interface PsGearMapper {
    /**
     * 向PS_GEAR表插入数据
     * @param psGear
     * @return
     */
    Integer insertPsGear(PsGear psGear);
}
