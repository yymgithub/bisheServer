package com.liang.demo.service;

import com.liang.demo.domain.PsDrive;

/**
 * Created by 永远有多远 on 2018/4/10.
 */
public interface PsDriveService {
    /**
     * PS_DRVIE插入数据是否成功
     * @param psDrive
     * @return
     */
    boolean insertPsDrive(PsDrive psDrive);
}
