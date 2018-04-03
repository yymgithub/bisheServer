package com.liang.demo.service;

import com.liang.demo.domain.PsCommand;

/**
 * Created by 永远有多远 on 2018/4/3.
 */
public interface PsCommandService {
    /**
     * 向PS_COMMAND表中出入命令控制数据
     * @param psCommand
     * @return
     */
    Integer insertPsCommand(PsCommand psCommand);

    /**
     * 停车操作后对相应台架的所有数据进行改动，全部参数改为0
     * @return
     */
    boolean commandStop(Integer psId);

    /**
     * 命令控制点击启动变频器改变参数表、台架表
     * @return
     */
    boolean commandStartUp(Integer psId);
}
