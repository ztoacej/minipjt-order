package com.sparta.minipjtorder.product.service;

import com.sparta.minipjtorder.product.dto.ProductCreateRequest;
import com.sparta.minipjtorder.product.dto.ProductResponse;
import com.sparta.minipjtorder.product.dto.ProductUpdateRequest;
import com.sparta.minipjtorder.product.entity.Product;
import com.sparta.minipjtorder.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sparta.minipjtorder.global.exception.ProductNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long create(ProductCreateRequest request) {

        Product product = new Product(
                request.getName(),
                request.getPrice(),
                request.getStock()
        );

        return productRepository.save(product).getId();
    }

    public ProductResponse findOne(Long id) {

        Product product = findProduct(id);

        return ProductResponse.from(product);
    }

    public List<ProductResponse> findAll() {

        return productRepository.findAllByDeletedFalse()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Transactional
    public void update(Long id, ProductUpdateRequest request) {

        Product product = findProduct(id);

        product.update(
                request.getName(),
                request.getPrice(),
                request.getStock()
        );
    }

    @Transactional
    public void delete(Long id) {

        Product product = findProduct(id);

        product.delete();
    }

    // 상품 조회 공통 로직
    // 중복 제거를 위해 별도 메서드로 추출
    private Product findProduct(Long id) {

        return productRepository
                .findByIdAndDeletedFalse(id)
                .orElseThrow(ProductNotFoundException::new);
//refactoring
//                .orElseThrow(() ->
//                        new IllegalArgumentException("상품이 존재하지 않습니다.")
//                );
    }
}