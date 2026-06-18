package com.sparta.minipjtorder.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class OrderCreateRequest {

    @Schema(description = "상품 ID", example = "1")
    private Long productId;
    @Schema(description = "주문 수량", example = "2")
    private Integer quantity;
}