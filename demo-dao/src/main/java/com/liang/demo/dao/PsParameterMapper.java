package com.liang.demo.dao;

import com.liang.demo.domain.PsParameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/1.
 */
@Repository
public interface PsParameterMapper {
    /**
     * 通过台架号获取台架的所有参数信息
     * @param psId
     * @return
     */
    List<PsParameter> getPsParameterByPsId(@Param("psId") Integer psId);

    /**
     * 更新参数表通过主键paraId
     * @param psParameter
     * @return
     */
    Integer updatePsParameterByParaId(PsParameter psParameter);
}
