package com.example.stock_springboot.dto;

import com.example.stock_springboot.entity.TransactionEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionResponseDto {
    private String playerId;
    private String stockName;
    private String type;
    private int quantity;
    private int price;
    private int totalAmount;
    private int profit;
    private LocalDateTime timestamp;

    public TransactionResponseDto(TransactionEntity transaction) {
        this.playerId = transaction.getPlayerId();
        this.stockName = transaction.getStockName();
        this.type = transaction.getType();
        this.quantity = transaction.getQuantity();
        this.price = transaction.getPrice();
        this.totalAmount = transaction.getTotalAmount();
        this.profit = transaction.getProfit();
        this.timestamp = transaction.getTimestamp();
    }
}
