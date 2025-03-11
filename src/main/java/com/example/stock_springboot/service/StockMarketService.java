package com.example.stock_springboot.service;

import com.example.stock_springboot.entity.PlayerEntity;
import com.example.stock_springboot.entity.PlayerStockEntity;
import com.example.stock_springboot.entity.TransactionEntity;
import com.example.stock_springboot.repository.PlayerRepository;
import com.example.stock_springboot.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockMarketService {
    private final PlayerRepository playerRepository;
    private final StockService stockService;
    private final TransactionRepository transactionRepository;

    public StockMarketService(PlayerRepository playerRepository, StockService stockService, TransactionRepository transactionRepository) {
        this.playerRepository = playerRepository;
        this.stockService = stockService;
        this.transactionRepository = transactionRepository;
    }

    public boolean buyStock(String playerId, String stockName, int quantity) {
        Optional<PlayerEntity> playerOpt = playerRepository.findById(playerId);
        if (playerOpt.isEmpty()) return false;

        PlayerEntity player = playerOpt.get();
        Double currentPrice = stockService.getStockPrice(stockName);
        if (currentPrice == null) return false;

        int totalCost = currentPrice.intValue() * quantity;
        if (player.getPlayerMoney() < totalCost) return false;

        boolean exists = false;
        for (PlayerStockEntity ps : player.getPlayerStockList()) {
            if (ps.getName().equals(stockName)) {
                ps.setStockQuantity(ps.getStockQuantity() + quantity);
                exists = true;
                break;
            }
        }

        if (!exists) {
            PlayerStockEntity newStock = new PlayerStockEntity();
            newStock.setName(stockName);
            newStock.setPrice(currentPrice.intValue());
            newStock.setStockQuantity(quantity);
            newStock.setPlayer(player);
            player.getPlayerStockList().add(newStock);
        }

        player.setPlayerMoney(player.getPlayerMoney() - totalCost);
        playerRepository.save(player);

        TransactionEntity transaction = new TransactionEntity(playerId, stockName, "buy", quantity, currentPrice.intValue(), 0);
        transactionRepository.save(transaction);
        return true;
    }

    public boolean sellStock(String playerId, String stockName, int quantity) {
        Optional<PlayerEntity> playerOpt = playerRepository.findById(playerId);
        if (playerOpt.isEmpty()) return false;

        PlayerEntity player = playerOpt.get();
        PlayerStockEntity stockToSell = null;
        for (PlayerStockEntity ps : player.getPlayerStockList()) {
            if (ps.getName().equals(stockName) && ps.getStockQuantity() >= quantity) {
                stockToSell = ps;
                break;
            }
        }

        if (stockToSell == null) return false;
        Double currentPrice = stockService.getStockPrice(stockName);
        if (currentPrice == null) return false;

        int sellPrice = currentPrice.intValue() * quantity;
        int buyPrice = stockToSell.getPrice() * quantity;
        int profit = sellPrice - buyPrice;

        stockToSell.setStockQuantity(stockToSell.getStockQuantity() - quantity);
        if (stockToSell.getStockQuantity() == 0) {
            player.getPlayerStockList().remove(stockToSell);
        }

        player.setPlayerMoney(player.getPlayerMoney() + sellPrice);
        playerRepository.save(player);

        TransactionEntity transaction = new TransactionEntity(playerId, stockName, "sell", quantity, currentPrice.intValue(), profit);
        transactionRepository.save(transaction);
        return true;
    }
}
