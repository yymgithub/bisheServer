package com.liang.demo.service;

import com.liang.demo.domain.PsLoad;

/**
 * Created by 永远有多远 on 2018/4/11.
 */
public interface PsLoadService {
    /**
     * APP完成手动控制负载电机模块实现向PS_LOAD表记录
     * @param psLoad
     * @return
     */
  boolean insertPsload(PsLoad psLoad);
}
