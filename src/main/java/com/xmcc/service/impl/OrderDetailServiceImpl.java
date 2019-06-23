package com.xmcc.service.impl;

import com.xmcc.dao.impl.BatchDaoImpl;
import com.xmcc.entity.OrderDetail;
import com.xmcc.service.OrderDetailService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 继承dao层中 写的batchInsert方法
 */
@Service
public class OrderDetailServiceImpl extends BatchDaoImpl<OrderDetail> implements OrderDetailService {

    @Override
    @Transactional//加入事务管理
    public void batchInsert(List<OrderDetail> list) {

        super.batchInsert(list);
    }
}