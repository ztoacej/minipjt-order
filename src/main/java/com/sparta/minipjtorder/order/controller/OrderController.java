package com.sparta.minipjtorder.order.controller;

import com.sparta.minipjtorder.global.response.ApiResponse;
import com.sparta.minipjtorder.order.dto.OrderCreateRequest;
import com.sparta.minipjtorder.order.dto.OrderResponse;
import com.sparta.minipjtorder.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "주문 API", description = "주문 관리 기능")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 생성")
    @PostMapping
    public ApiResponse<Long> create(
            @RequestBody OrderCreateRequest request
    ) {

        Long id = orderService.create(request);

        return new ApiResponse<>(
                "주문 생성 완료",
                id
        );
    }
//refactoring
//    @PostMapping
//    public Long create(
//            @RequestBody OrderCreateRequest request
//    ) {
//        return orderService.create(request);
//    }

    @Operation(summary = "주문 단건 조회")
    @GetMapping("/{id}")
    public OrderResponse findOne(
            @PathVariable Long id
    ) {
        return orderService.findOne(id);
    }

    @Operation(summary = "주문 목록 조회 (페이지네이션)")
    @GetMapping
    public Page<OrderResponse> findAll(
            @PageableDefault(size = 10)
            Pageable pageable
    ) {
        return orderService.findAll(pageable);
    }
}