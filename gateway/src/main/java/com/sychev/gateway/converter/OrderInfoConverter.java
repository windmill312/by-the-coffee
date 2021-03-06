package com.sychev.gateway.converter;

import com.sychev.common.grpc.model.GPage;
import com.sychev.gateway.web.to.common.PagedResult;
import com.sychev.gateway.web.to.in.AddOrderRequest;
import com.sychev.gateway.web.to.in.UpdateOrderRequest;
import com.sychev.gateway.web.to.in.common.OrderProducts;
import com.sychev.gateway.web.to.in.common.OrderStatus;
import com.sychev.gateway.web.to.out.OrderInfo;
import com.sychev.order.grpc.model.v1.GAddOrderRequest;
import com.sychev.order.grpc.model.v1.GOrderInfo;
import com.sychev.order.grpc.model.v1.GUpdateOrderRequest;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class OrderInfoConverter {

    public static PagedResult<OrderInfo> convert(
            List<GOrderInfo> OrderInfoList, GPage page) {
        return new PagedResult<>(
                page.getTotalElements(),
                OrderInfoList.stream()
                        .map(OrderInfoConverter::convert)
                        .collect(Collectors.toList()));
    }

    public static OrderInfo convert(GOrderInfo orderInfo) {
        return new OrderInfo(
                CommonConverter.convert(orderInfo.getOrderUid()),
                CommonConverter.convert(orderInfo.getCafeUid()),
                CommonConverter.convert(orderInfo.getCustomerUid()),
                orderInfo.getProductsList().stream()
                        .map(OrderInfoConverter::convert)
                        .collect(Collectors.toSet()),
                orderInfo.getPrice(),
                Instant.ofEpochMilli(orderInfo.getCreateMills()),
                Instant.ofEpochMilli(orderInfo.getReceiveMills()),
                convert(orderInfo.getStatus()));
    }

    private static OrderStatus convert(GOrderInfo.OrderStatus status) {
        return OrderStatus.valueOf(status.name());
    }

    private static GOrderInfo.OrderStatus convert(OrderStatus status) {
        return GOrderInfo.OrderStatus.valueOf(status.name());
    }

    private static OrderProducts convert(GOrderInfo.OrderProduct c) {
        return new OrderProducts()
                .setProductUid(CommonConverter.convert(c.getProductUid()))
                .setQuantity(c.getQuantity());
    }

    private static GOrderInfo.OrderProduct convert(OrderProducts c) {
        return GOrderInfo.OrderProduct.newBuilder()
                .setProductUid(CommonConverter.convert(c.getProductUid()))
                .setQuantity(c.getQuantity())
                .build();
    }


    public static GAddOrderRequest convert(AddOrderRequest request) {
        return GAddOrderRequest.newBuilder()
                .setOrder(GOrderInfo.newBuilder()
                        .setStatus(convert(request.getStatus()))
                        .setCafeUid(CommonConverter.convert(request.getCafeUid()))
                        .setCustomerUid(CommonConverter.convert(request.getCustomerUid()))
                        .addAllProducts(request.getProducts().stream()
                                .map(OrderInfoConverter::convert)
                                .collect(Collectors.toSet()))
                        .setPrice(request.getTotalPrice())
                        .setCreateMills(request.getCreateDttm().toEpochMilli())
                        .setReceiveMills(request.getReceiveDttm().toEpochMilli())
                        .build())
                .build();
    }

    public static GUpdateOrderRequest convert(UpdateOrderRequest request) {
        return GUpdateOrderRequest.newBuilder()
                .setOrder(GOrderInfo.newBuilder()
                        .setOrderUid(CommonConverter.convert(request.getOrderUid()))
                        .setStatus(convert(request.getStatus()))
                        .setCafeUid(CommonConverter.convert(request.getCafeUid()))
                        .setCustomerUid(CommonConverter.convert(request.getCustomerUid()))
                        .addAllProducts(request.getProducts().stream()
                                .map(OrderInfoConverter::convert)
                                .collect(Collectors.toSet()))
                        .setPrice(request.getTotalPrice())
                        .setCreateMills(request.getCreateDttm().toEpochMilli())
                        .setReceiveMills(request.getReceiveDttm().toEpochMilli())
                        .build())
                .build();
    }
}
