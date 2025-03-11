package com.example.stock_springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "players")
public class PlayerEntity {
    @Id
    private String playerId;
    
    private int playerMoney;
    private int profit;
    private double profitRate;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerStockEntity> playerStockList = new ArrayList<>();

    //  직접 Getter 추가 (JPA 컬렉션 필드는 명시적으로 Getter를 추가해야 함)
    public List<PlayerStockEntity> getPlayerStockList() {
        if (playerStockList == null) {
            playerStockList = new ArrayList<>();
        }
        return playerStockList;
    }
}
