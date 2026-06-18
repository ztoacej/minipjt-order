package com.sparta.minipjtorder.order.dto;
import com.sparta.minipjtorder.order.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private String productName;
    private Integer quantity;

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProduct().getName(),
                order.getQuantity()
        );
    }
}