package com.xmcc.service;

import com.xmcc.common.ResultResponse;
import com.xmcc.dto.OrderMasterDto;

public interface OrderMasterService {
    //插入订单
    ResultResponse insertOrder(OrderMasterDto orderMasterDto);
}
