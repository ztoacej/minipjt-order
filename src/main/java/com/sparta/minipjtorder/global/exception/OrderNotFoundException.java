package com.sparta.minipjtorder.global.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("주문이 존재하지 않습니다.");
    }
}