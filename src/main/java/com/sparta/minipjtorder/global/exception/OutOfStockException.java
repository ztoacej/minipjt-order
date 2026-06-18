package com.sparta.minipjtorder.global.exception;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException() {
        super("재고가 부족합니다.");
    }
}