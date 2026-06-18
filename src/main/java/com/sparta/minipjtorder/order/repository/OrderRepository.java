package com.sparta.minipjtorder.order.repository;

import com.sparta.minipjtorder.order.entity.Order;

//import java.awt.print.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 주문 조회 시 상품 정보를 함께 조회하여 N+1 문제를 방지(단건 조회)
    @Query("""
        select o
        from Order o
        join fetch o.product
        where o.id = :id
    """)
    Optional<Order> findDetail(Long id);
    // 주문 목록 조회 시 상품 정보를 함께 조회하여 N+1 문제를 방지
    @Query(
            value = """
                select o
                from Order o
                join fetch o.product
            """,
            countQuery = """
                select count(o)
                from Order o
            """
    )
    Page<Order> findAllWithProduct(Pageable pageable);
}