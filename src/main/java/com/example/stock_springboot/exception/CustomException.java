package com.example.stock_springboot.exception;
//playerstocklist null 방지
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
