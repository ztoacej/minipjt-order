package com.sparta.minipjtorder.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ProductUpdateRequest {

    @Schema(description = "상품명", example = "사이다")
    private String name;
    @Schema(description = "가격", example = "2500")
    private Integer price;
    @Schema(description = "재고", example = "50")
    private Integer stock;
}
