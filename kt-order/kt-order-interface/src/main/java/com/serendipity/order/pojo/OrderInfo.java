package com.serendipity.order.pojo;

import java.io.Serializable;
import java.util.List;

import com.serendipity.pojo.TbOrder;
import com.serendipity.pojo.TbOrderItem;
import com.serendipity.pojo.TbOrderShipping;

public class OrderInfo extends TbOrder implements Serializable {
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }

}
