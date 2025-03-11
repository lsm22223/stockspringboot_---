package com.example.stock_springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String playerId;
    private String stockName;
    private String type; // buy or sell
    private int quantity;
    private int price;
    private int totalAmount;
    private int profit;
    private LocalDateTime timestamp;

    public TransactionEntity(String playerId, String stockName, String type, int quantity, int price, int profit) {
        this.playerId = playerId;
        this.stockName = stockName;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = quantity * price;
        this.profit = profit;
        this.timestamp = LocalDateTime.now();
    }
}
