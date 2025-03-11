package com.example.stock_springboot.dto;

import com.example.stock_springboot.entity.StockEntity;
import lombok.Getter;

@Getter
public class StockResponseDto {
    private String name;
    private int price;

    public StockResponseDto(StockEntity stock) {
        this.name = stock.getName();
        this.price = stock.getPrice();
    }
}
