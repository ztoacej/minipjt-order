package com.sparta.minipjtorder.order.service;

import com.sparta.minipjtorder.order.dto.OrderCreateRequest;
import com.sparta.minipjtorder.order.dto.OrderResponse;
import com.sparta.minipjtorder.order.entity.Order;
import com.sparta.minipjtorder.order.repository.OrderRepository;
import com.sparta.minipjtorder.product.entity.Product;
import com.sparta.minipjtorder.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sparta.minipjtorder.global.exception.OrderNotFoundException;
import com.sparta.minipjtorder.global.exception.ProductNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long create(OrderCreateRequest request) {

        Product product = findProduct(
                request.getProductId()
        );

        product.decreaseStock(
                request.getQuantity()
        );

        Order order = new Order(
                product,
                request.getQuantity()
        );

        return orderRepository.save(order)
                .getId();
    }

    public OrderResponse findOne(Long id) {

        Order order = findOrder(id);

        return OrderResponse.from(order);
    }

    public Page<OrderResponse> findAll(Pageable pageable) {

        return orderRepository.findAllWithProduct(pageable)
                .map(OrderResponse::from);
    }

    // 상품 조회 공통 로직
    private Product findProduct(Long productId) {

        return productRepository
                .findByIdForUpdate(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

    // 상품 조회 공통 로직
    private Order findOrder(Long orderId) {

        return orderRepository
                .findDetail(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

//refactoring
//    private Product findProduct(Long productId) {
//
//        return productRepository
//                .findByIdForUpdate(productId)
//                .orElseThrow(() ->
//                        new IllegalArgumentException("상품이 존재하지 않습니다.")
//                );
//    }
//
//    private Order findOrder(Long orderId) {
//
//        return orderRepository
//                .findDetail(orderId)
//                .orElseThrow(() ->
//                        new IllegalArgumentException("주문이 존재하지 않습니다.")
//                );
//    }
}