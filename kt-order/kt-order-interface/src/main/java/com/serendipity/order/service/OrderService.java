package com.serendipity.order.service;

import com.serendipity.common.utils.E3Result;
import com.serendipity.order.pojo.OrderInfo;

public interface OrderService {
    E3Result createOrder(OrderInfo orderInfo);
}
