package com.liang.demo.dao;

import com.liang.demo.domain.PsCommand;
import org.springframework.stereotype.Repository;

/**
 * Created by 永远有多远 on 2018/4/3.
 */
@Repository
public interface PsCommandMapper {
    /**
     *APP端进行命令控制操作时候插入该表进行操作记录
     * @param psCommand
     * @return
     */
    Integer insertPsCommand(PsCommand psCommand);
}
