package com.sparta.minipjtorder.product.repository;

import com.sparta.minipjtorder.product.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    // 주문 데이터와의 정합성을 위해 물리 삭제 대신 Soft Delete를 사용
    Optional<Product> findByIdAndDeletedFalse(Long id);

    List<Product> findAllByDeletedFalse();

    // 동시 주문 상황에서 재고 정합성을 보장하기 위해 비관적 락 적용
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select p
        from Product p
        where p.id = :id
          and p.deleted = false
    """)
    Optional<Product> findByIdForUpdate(Long id);
}
