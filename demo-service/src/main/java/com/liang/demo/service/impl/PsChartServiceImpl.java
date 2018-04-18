package com.liang.demo.service.impl;

import com.liang.demo.dao.PsChartMapper;
import com.liang.demo.domain.PsChart;
import com.liang.demo.service.PsChartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/17.
 */
@Service
public class PsChartServiceImpl implements PsChartService {
    public static final Logger logger = LogManager.getLogger(PsChartServiceImpl.class);
    @Resource
    private PsChartMapper psChartMapper;
    @Override
    public List<PsChart> getPsChartByPsId(Integer psId) {
        if (psId == null) return null;
        try {
            List<PsChart> psChartList =psChartMapper.getPsChartByPsId(psId);
            return psChartList;
        } catch (Throwable e) {
            logger.error("获取最近chart表数据时service层异常", e);
        }
        return null;
    }

    /**
     * 向PsChart表插入数据
     *
     * @param psChart
     * @return
     */
    @Override
    public boolean insertPsChart(PsChart psChart) {
        if(psChart==null) return false;
        try{
            Integer res=psChartMapper.insertPsChart(psChart);
            if(res==1) return true;
        }catch(Throwable e){
            logger.error("向表PS_CHART插入数据时Service层出错");
        }

        return false;
    }
}
