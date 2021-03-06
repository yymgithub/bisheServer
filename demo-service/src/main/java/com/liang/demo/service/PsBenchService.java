package com.liang.demo.service;

import com.liang.demo.domain.PsBench;
import java.util.List;

/**
 * Created by 永远有多远 on 2018/3/30.
 */
public interface PsBenchService {
    /**
     * 获得所有台架名称和ID
     * @return
     */
    List<PsBench> getAllPsBench();

    /**
     * APP进行命令控制时通过PSID获取台架停车或者报警信息进行判断和操作
     * @param psId
     * @return
     */
    PsBench selectPsBenchByPsId(Integer psId);
}
