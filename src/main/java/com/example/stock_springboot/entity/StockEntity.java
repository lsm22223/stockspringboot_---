package com.example.stock_springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "stocks")
public class StockEntity {
    @Id
    private String name; // Primary Key (자동 증가 없음)
    
    private int price;
}
