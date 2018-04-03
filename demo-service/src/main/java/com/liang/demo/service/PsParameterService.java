package com.liang.demo.service;

import com.liang.demo.domain.PsParameter;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/1.
 */
public interface PsParameterService {
    /**
     * 通过台架Id获取该台架有关的所有参数信息
     * @param psId
     * @return
     */
    List<PsParameter> getPsParameterByPsId(Integer psId);
}
