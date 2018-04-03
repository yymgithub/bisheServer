package com.liang.demo.service.impl;

import com.liang.demo.dao.PSBenchMapper;
import com.liang.demo.dao.PsCommandMapper;
import com.liang.demo.dao.PsParameterMapper;
import com.liang.demo.domain.PsBench;
import com.liang.demo.domain.PsCommand;
import com.liang.demo.domain.PsParameter;
import com.liang.demo.service.PsCommandService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 永远有多远 on 2018/4/3.
 */
@Service
public class PsCommandServiceImpl implements PsCommandService {
    public static final Logger logger = LogManager.getLogger(PsCommandServiceImpl.class);

    @Resource
    private PsCommandMapper psCommandMapper;
    @Resource
    private PsParameterMapper psParameterMapper;
    @Resource
    private PSBenchMapper psBenchMapper;
    /**
     * 向PS_COMMAND表中出入命令控制数据
     *
     * @param psCommand
     * @return
     */
    @Override
    public Integer insertPsCommand(PsCommand psCommand) {
        if(psCommand==null)  return 0;
        Integer result=0;
        try{
            result=psCommandMapper.insertPsCommand(psCommand);
        }catch (Throwable e){
            logger.error("向PS_COMMAND表中出入数据Service层出错",e);
        }
        return result;
    }

    /**
     * 停车操作后对相应台架的所有数据进行改动，全部参数改为0
     *
     * @return
     */
    @Override
    public boolean commandStop(Integer psId) {
        List<PsParameter> psParameterList=new ArrayList<>();
        if(psId==null) return false;
        try{
            psParameterList=psParameterMapper.getPsParameterByPsId(psId);
            if(psParameterList==null) return false;
            else{
                Integer num=psParameterList.size();
                for(int i=0;i<num;i++){
                    psParameterList.get(i).setParaValue(0);
                    Integer result=psParameterMapper.updatePsParameterByParaId( psParameterList.get(i));
                    if(result!=1) return false;
                }
                PsBench psBench=new PsBench();
                psBench.setPsId(psId);
                psBench.setPsStop(1);
               Integer res3= psBenchMapper.updatePsBenchStop(psBench);
                if(res3!=1) return false;
                return true;
            }
        }catch (Throwable e){
            logger.error("命令控制停车改动参数表所有参数为0时Service层出错");
        }

        return false;
    }

    /**
     * 命令控制点击启动变频器改变参数表、台架表
     *
     * @param psId
     * @return
     */
    @Override
    public boolean commandStartUp(Integer psId) {
        if(psId==null) return false;
        try{
            List<PsParameter> psParameterList=psParameterMapper.getPsParameterByPsId(psId);
            if(psParameterList==null) return false;
            else{
                Integer num=psParameterList.size();
                for(int i=0;i<num;i++){
                    psParameterList.get(i).setParaValue(Math.random()*1500);
                    Integer result=psParameterMapper.updatePsParameterByParaId( psParameterList.get(i));
                    if(result!=1) return false;
                }
                PsBench psBench=new PsBench();
                psBench.setPsId(psId);
                psBench.setPsStop(0);
                Integer res3= psBenchMapper.updatePsBenchStop(psBench);
                if(res3!=1) return false;
                return true;
            }
        }catch (Throwable e){
            logger.error("命令控制停车改动参数表所有参数为0时Service层出错");
        }

        return false;
    }
}
