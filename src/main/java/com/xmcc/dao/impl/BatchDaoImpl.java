package com.xmcc.dao.impl;

import com.xmcc.dao.BatchDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *  实现批量数据的操作
 */
public class BatchDaoImpl<T> implements BatchDao<T> {

    //获取操作批量数据的管理器
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 批量插入数据
     * @param list
     */

    @Override
    public void batchInsert(List<T> list) {
        long size = list.size();
        //循环存入 缓冲区
        for (int i = 0; i <size ; i++) {

            entityManager.persist(list.get(i));
            //自定义写入数据库的条件
              //1、当有100条，则写入数据库
              //2、如果没有100条，就直接写入数据库
            if (i%100==0||i==size-1){
                 //刷新缓冲区
                entityManager.flush();
                //清理缓冲区
                entityManager.clear();
            }
        }


    }
}