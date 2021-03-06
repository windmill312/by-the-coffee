package com.sychev.gateway.grpc.client;

import com.sychev.common.grpc.model.Empty;
import com.sychev.product.grpc.model.v1.*;
import com.sychev.product.grpc.service.v1.ProductServiceV1Grpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GRpcProductServiceClient {

    private ProductServiceV1Grpc.ProductServiceV1BlockingStub productServiceV1BlockingStub;

    @Value("#{new String('${gateway.grpc.client.ProductServiceV1Grpc.host}')}")
    private String host;
    @Value("#{new Integer('${gateway.grpc.client.ProductServiceV1Grpc.port}')}")
    private int port;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        productServiceV1BlockingStub = ProductServiceV1Grpc.newBlockingStub(managedChannel);
    }

    public GGetProductResponse getProduct(GGetProductRequest request) {
        return productServiceV1BlockingStub.getProduct(request);
    }

    public GGetAllProductsResponse getAllProducts(GGetAllProductsRequest request) {
        return productServiceV1BlockingStub.getAllProducts(request);
    }

    public GGetProductsByCafeResponse getAllProducts(GGetProductsByCafeRequest request) {
        return productServiceV1BlockingStub.getProductsByCafe(request);
    }

    public GAddProductResponse addProduct(GAddProductRequest request) {
        return productServiceV1BlockingStub.addProduct(request);
    }

    public Empty updateProduct(GUpdateProductRequest request) {
        return productServiceV1BlockingStub.updateProduct(request);
    }

    public Empty linkProductAndCafe(GLinkProductAndCafeRequest request) {
        return productServiceV1BlockingStub.linkProductAndCafe(request);
    }

    public Empty unlinkProductAndCafe(GUnlinkProductAndCafeRequest request) {
        return productServiceV1BlockingStub.unlinkProductAndCafe(request);
    }

    public Empty removeProduct(GRemoveProductRequest request) {
        return productServiceV1BlockingStub.removeProduct(request);
    }

    public Empty removeProductsByCafe(GRemoveProductsByCafeRequest request) {
        return productServiceV1BlockingStub.removeProductsByCafe(request);
    }


}
