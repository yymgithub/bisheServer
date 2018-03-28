package com.liang.demo.util;

import com.liang.demo.domain.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 永远有多远 on 2018/3/28.
 */
@Data
public class WeList {

    public static List<User> userList = new ArrayList<>();

    public static void addList(List list){
        userList = list;
    }
}
