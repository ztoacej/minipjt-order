package com.sparta.minipjtorder.product.dto;

import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
public class ProductCreateRequest {

    @Schema(description = "상품명", example = "콜라")
    private String name;
    @Schema(description = "가격", example = "2000")
    private Integer price;
    @Schema(description = "재고", example = "100")
    private Integer stock;
}