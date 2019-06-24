package com.xmcc.service;

import com.xmcc.beans.DetailShowBean;
import com.xmcc.beans.PageBean;
import com.xmcc.common.ResultResponse;
import com.xmcc.dto.OrderMasterDto;

public interface OrderMasterService {
    //插入订单
    ResultResponse insertOrder(OrderMasterDto orderMasterDto);

    //分页显示订单列表
    ResultResponse findOrderMasterList(PageBean pageBean);


    //获取订单详情
    ResultResponse findOrderDetailList(DetailShowBean detailShowBean);

    //取消订单
    ResultResponse cancelOrder(DetailShowBean detailShowBean);

}
