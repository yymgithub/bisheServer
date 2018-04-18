package com.liang.demo.service;

import com.liang.demo.domain.PsChart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/17.
 */
public interface PsChartService {
    /* 获取最近时间的前6条数据
    * @return
            */
    List<PsChart> getPsChartByPsId(Integer psId);

    /**
     * 向PsChart表插入数据
     * @param psChart
     * @return
     */
    boolean insertPsChart(PsChart psChart);
}
