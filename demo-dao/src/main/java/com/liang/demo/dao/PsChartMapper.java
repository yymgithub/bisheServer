package com.liang.demo.dao;

import com.liang.demo.domain.PsChart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/17.
 */
@Repository
public interface PsChartMapper {
    /**
     * 获取最近时间的前6条数据
     * @return
     */
    List<PsChart> getPsChartByPsId(@Param("psId") Integer psId);

    /**
     * 向PsChart表插入数据
     * @param psChart
     * @return
     */
    Integer insertPsChart(PsChart psChart);

}
