package com.sychev.gateway.service;

import com.sychev.gateway.web.to.common.PagedResult;
import com.sychev.gateway.web.to.in.AddOrderRequest;
import com.sychev.gateway.web.to.in.UpdateOrderRequest;
import com.sychev.gateway.web.to.out.OrderInfo;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    PagedResult<OrderInfo> getAllOrders(int page, int size);

    List<OrderInfo> getAllOrdersByCustomer(UUID customerUid);

    OrderInfo getOrderByUid(UUID orderUid);

    UUID addOrder(AddOrderRequest request);

    void updateOrder(UpdateOrderRequest request);

    void removeOrder(UUID orderUid);

    void removeAllOrdersByCustomer(UUID customerUid);

}
