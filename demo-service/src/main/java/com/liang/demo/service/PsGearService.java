package com.liang.demo.service;

import com.liang.demo.domain.PsGear;

/**
 * Created by 永远有多远 on 2018/4/12.
 */
public interface PsGearService {
    /**
     * APP档位控制换挡操作记录
     * @param psGear
     * @return
     */
    boolean insertPsGear(PsGear psGear);
}
