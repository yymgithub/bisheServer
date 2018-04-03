package com.liang.demo.dao;

import com.liang.demo.domain.PsBench;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/3/30.
 */
@Repository
public interface PSBenchMapper {

    List<PsBench> getAllPsBench();

    /**
     * APP进行命令控制时通过PSID获取台架停车或者报警信息进行判断和操作
     * @param psId
     * @return
     */
    PsBench selectPsBenchByPsId(@Param("psId") Integer psId);

    /**
     * 通过台架号更新修改台架表的Stop字段
     * @param psBench
     * @return
     */
    Integer updatePsBenchStop(PsBench psBench);
    /**
     * 通过台架号更新修改台架表的ALARM字段
     * @param psBench
     * @return
     */
    Integer updatePsBenchAlarm(PsBench psBench);
}
