package com.example.stock_springboot.dto;

import com.example.stock_springboot.entity.PlayerStockEntity;
import lombok.Getter;

@Getter
public class PlayerStockResponseDto {
    private String name;
    private int price;
    private int stockQuantity;

    public PlayerStockResponseDto(PlayerStockEntity playerStock) {
        this.name = playerStock.getName();
        this.price = playerStock.getPrice();
        this.stockQuantity = playerStock.getStockQuantity();
    }
}
