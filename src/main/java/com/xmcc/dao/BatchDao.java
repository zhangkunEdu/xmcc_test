package com.xmcc.dao;

import java.util.List;

/**
 *  实现批量数据的操作
 */
public interface BatchDao<T> {

    //批量插入
    void batchInsert(List<T> list);
}