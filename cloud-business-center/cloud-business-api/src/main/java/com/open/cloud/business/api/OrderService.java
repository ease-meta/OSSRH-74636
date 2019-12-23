package com.open.cloud.business.api;

import com.open.cloud.business.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    Order create(String userId, String commodityCode, int orderCount);
}