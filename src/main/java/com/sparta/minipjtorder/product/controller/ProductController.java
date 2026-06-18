package com.sparta.minipjtorder.product.controller;

import com.sparta.minipjtorder.global.response.ApiResponse;
import com.sparta.minipjtorder.product.dto.ProductCreateRequest;
import com.sparta.minipjtorder.product.dto.ProductResponse;
import com.sparta.minipjtorder.product.dto.ProductUpdateRequest;
import com.sparta.minipjtorder.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "상품 API", description = "상품 관리 기능")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 등록")
    @PostMapping
    public ApiResponse<Long> create(
            @RequestBody ProductCreateRequest request
    ) {

        Long id = productService.create(request);

        return new ApiResponse<>(
                "상품 생성 완료",
                id
        );
    }
//refactoring
//    @PostMapping
//    public Long create(
//            @RequestBody ProductCreateRequest request
//    ) {
//        return productService.create(request);
//    }

    @Operation(summary = "상품 단건 조회")
    @GetMapping("/{id}")
    public ProductResponse findOne(
            @PathVariable Long id
    ) {
        return productService.findOne(id);
    }

    @Operation(summary = "상품 목록 조회")
    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @Operation(summary = "상품 수정")
    @PutMapping("/{id}")
    public void update(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest request
    ) {
        productService.update(id, request);
    }

    @Operation(summary = "상품 삭제 (Soft Delete)")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        productService.delete(id);
    }
}