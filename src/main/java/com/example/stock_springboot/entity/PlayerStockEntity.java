package com.example.stock_springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "player_stocks")
public class PlayerStockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;
}
