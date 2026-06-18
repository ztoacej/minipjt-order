package com.sparta.minipjtorder.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.sparta.minipjtorder.global.exception.OutOfStockException;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer stock;

    // 주문 데이터와의 정합성을 위해 물리 삭제 대신 Soft Delete를 사용
    private Boolean deleted;

    public Product(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.deleted = false;
    }

    public void update(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // 실제 삭제(Hard Delete) 대신 논리 삭제 처리
    public void delete() {
        this.deleted = true;
    }

    // 주문 시 재고 차감
    // 재고 부족 시 주문 생성 불가
    public void decreaseStock(int quantity) {
        if (stock < quantity) {
            throw new OutOfStockException();
            //refactoring
            //throw new IllegalArgumentException("재고 부족");
        }

        stock -= quantity;
    }
}
