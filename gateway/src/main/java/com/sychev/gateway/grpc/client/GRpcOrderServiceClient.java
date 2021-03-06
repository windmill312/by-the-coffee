package com.sychev.gateway.grpc.client;

import com.sychev.common.grpc.model.Empty;
import com.sychev.order.grpc.model.v1.*;
import com.sychev.order.grpc.service.v1.OrderServiceV1Grpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GRpcOrderServiceClient {

    private OrderServiceV1Grpc.OrderServiceV1BlockingStub orderServiceV1BlockingStub;

    @Value("#{new String('${gateway.grpc.client.OrderServiceV1Grpc.host}')}")
    private String host;
    @Value("#{new Integer('${gateway.grpc.client.OrderServiceV1Grpc.port}')}")
    private int port;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        orderServiceV1BlockingStub = OrderServiceV1Grpc.newBlockingStub(managedChannel);
    }

    public GGetOrderResponse getOrder(GGetOrderRequest request) {
        return orderServiceV1BlockingStub.getOrder(request);
    }

    public GGetAllOrdersResponse getAllOrders(GGetAllOrdersRequest request) {
        return orderServiceV1BlockingStub.getAllOrders(request);
    }

    public GGetAllOrdersByCustomerResponse getAllOrdersByCustomer(GGetAllOrdersByCustomerRequest request) {
        return orderServiceV1BlockingStub.getAllOrdersByCustomer(request);
    }

    public GAddOrderResponse addOrder(GAddOrderRequest request) {
        return orderServiceV1BlockingStub.addOrder(request);
    }

    public Empty updateOrder(GUpdateOrderRequest request) {
        return orderServiceV1BlockingStub.updateOrder(request);
    }

    public Empty removeOrder(GRemoveOrderRequest request) {
        return orderServiceV1BlockingStub.removeOrder(request);
    }

    public Empty removeAllOrdersByCustomer(GRemoveAllOrdersByCustomerRequest request) {
        return orderServiceV1BlockingStub.removeAllOrdersByCustomer(request);
    }


}
