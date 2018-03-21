package com.liang.demo.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/3/20.
 */
@Data
public class Page {

    //一共有多少条
    private Integer items;
    //当前是第几页
    private Integer page;
    //每页多少条
    private Integer itemsPerPage;
    //具体的数据
    private List datas;

}
