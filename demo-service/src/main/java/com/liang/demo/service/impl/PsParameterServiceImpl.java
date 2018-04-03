package com.liang.demo.service.impl;

import com.liang.demo.dao.PsParameterMapper;
import com.liang.demo.domain.PsParameter;
import com.liang.demo.service.PsParameterService;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;

/**
 * Created by 永远有多远 on 2018/4/1.
 */
@Service
public class PsParameterServiceImpl implements PsParameterService {

    public static final Logger logger = LogManager.getLogger(PsParameterServiceImpl.class);

    @Resource
    private PsParameterMapper psParameterMapper;
    /**
     * 通过台架Id获取该台架有关的所有参数信息
     *
     * @param psId
     * @return
     */
    @Override
    public List<PsParameter> getPsParameterByPsId(Integer psId) {
        if (psId == null) return null;
        List<PsParameter> psParameterList = new ArrayList<>();
        try {
          psParameterList = psParameterMapper.getPsParameterByPsId(psId);
        } catch (Throwable e) {
            logger.error("通过台架Id获取台架信息异常service层异常", e);
        }
        return psParameterList;
    }
}
