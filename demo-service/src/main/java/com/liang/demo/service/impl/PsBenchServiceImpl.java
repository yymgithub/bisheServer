package com.liang.demo.service.impl;

import com.liang.demo.dao.PSBenchMapper;
import com.liang.demo.dao.UserMapper;
import com.liang.demo.domain.PsBench;
import com.liang.demo.domain.User;
import com.liang.demo.service.PsBenchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 永远有多远 on 2018/3/30.
 */
@Service
public class PsBenchServiceImpl  implements PsBenchService {
    public static final Logger logger = LogManager.getLogger(PsBenchServiceImpl.class);

    @Resource
    private PSBenchMapper psBenchMapper;
    /**
     * 获得所有台架名称和ID
     *
     * @return
     */
    @Override
    public List<PsBench> getAllPsBench() {
        List<PsBench> benchList = new ArrayList<>();
        try {
            benchList = psBenchMapper.getAllPsBench();

        } catch (Throwable e) {
            logger.error("查看用户service层异常", e);
        }
        return benchList;

    }

    /**
     * APP进行命令控制时通过PSID获取台架停车或者报警信息进行判断和操作
     *
     * @param psId
     * @return
     */
    @Override
    public PsBench selectPsBenchByPsId(Integer psId) {
        if(psId==null) return null;
        try{
            PsBench psBench=psBenchMapper.selectPsBenchByPsId(psId);
            if(psBench==null) return null;
            return psBench;
        }catch (Throwable e){
            logger.error("通过psId获得台架停车和报警状态时Service层出错",e);
        }
        return null;
    }
}
