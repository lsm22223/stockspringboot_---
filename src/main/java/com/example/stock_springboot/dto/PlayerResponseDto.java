package com.example.stock_springboot.dto;

import com.example.stock_springboot.entity.PlayerEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlayerResponseDto {
    private String playerId;
    private int playerMoney;
    private int profit;
    private double profitRate;
    private List<PlayerStockResponseDto> playerStockList;
    
    // entity말고 dto 사용
    public PlayerResponseDto(PlayerEntity player) {
        this.playerId = player.getPlayerId();
        this.playerMoney = player.getPlayerMoney();
        this.profit = player.getProfit();
        this.profitRate = player.getProfitRate();
        this.playerStockList = player.getPlayerStockList()
                .stream()
                .map(PlayerStockResponseDto::new)
                .collect(Collectors.toList());
    }
}
