package com.liang.demo.service;

import com.liang.demo.domain.PsFault;

/**
 * Created by 永远有多远 on 2018/4/12.
 */
public interface PsFaultService  {
    /**
     * App实现故障控制操作记录
     * @param psFault
     * @return
     */
    boolean insertPsFault(PsFault psFault);
}
