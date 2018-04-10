package com.liang.demo.dao;

import com.liang.demo.domain.PsDrive;
import org.springframework.stereotype.Repository;

/**
 * Created by 永远有多远 on 2018/4/10.
 */
@Repository
public interface PsDriveMapper {
    /**
     * APP手动控制模块的手动控制下的驱动控制的给定插入PS_DRIVE表一条数据
     * @param psDrive
     * @return
     */
    Integer insertPsDrive(PsDrive psDrive);
}
