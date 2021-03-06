package com.sychev.gateway.web.to.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sychev.gateway.web.to.in.common.OrderProducts;
import com.sychev.gateway.web.to.in.common.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UpdateOrderRequest {

    @NotNull
    @ApiModelProperty(example = "78a8a5d0-8830-4ce4-9176-6a5d7f3dae34")
    private UUID orderUid;

    @NotNull
    @ApiModelProperty(example = "78a8a5d0-8830-4ce4-9176-6a5d7f3dae34")
    private UUID cafeUid;

    @NotNull
    @ApiModelProperty(example = "56a8a5d0-8830-6cbd-9176-6a5d7f3dae34")
    private UUID customerUid;

    @NotNull
    private Set<OrderProducts> products = new HashSet<>();

    @NotNull
    private Double totalPrice;

    @NotNull
    private Instant createDttm;

    @NotNull
    private Instant receiveDttm;

    @NotNull
    private OrderStatus status;

    @JsonCreator
    public UpdateOrderRequest(
            @JsonProperty("orderUid") UUID orderUid,
            @JsonProperty("cafeUid") UUID cafeUid,
            @JsonProperty("customerUid") UUID customerUid,
            @JsonProperty("products") Set<OrderProducts> products,
            @JsonProperty("totalPrice") Double totalPrice,
            @JsonProperty("createDttm") Instant createDttm,
            @JsonProperty("receiveDttm") Instant receiveDttm,
            @JsonProperty("status") OrderStatus status) {
        this.orderUid = orderUid;
        this.cafeUid = cafeUid;
        this.customerUid = customerUid;
        this.products = products;
        this.totalPrice = totalPrice;
        this.createDttm = createDttm;
        this.receiveDttm = receiveDttm;
        this.status = status;
    }

    public UUID getOrderUid() {
        return orderUid;
    }

    public UUID getCafeUid() {
        return cafeUid;
    }

    public UUID getCustomerUid() {
        return customerUid;
    }

    public Set<OrderProducts> getProducts() {
        return products;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Instant getCreateDttm() {
        return createDttm;
    }

    public Instant getReceiveDttm() {
        return receiveDttm;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

