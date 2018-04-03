package com.liang.demo.dao;

import com.liang.demo.domain.PsBench;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/3/30.
 */
@Repository
public interface PSBenchMapper {

    List<PsBench> getAllPsBench();
}
