package com.example.stock_springboot.config;

import com.example.stock_springboot.entity.PlayerEntity;
import com.example.stock_springboot.entity.StockEntity;
import com.example.stock_springboot.repository.PlayerRepository;
import com.example.stock_springboot.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Config {
    @Bean
    CommandLineRunner initDatabase(PlayerRepository playerRepository, StockRepository stockRepository) {
        return args -> {
            // 기본 플레이어 추가
            PlayerEntity player1 = new PlayerEntity();
            player1.setPlayerId("player1");
            player1.setPlayerMoney(10000);
            playerRepository.save(player1);

            PlayerEntity player2 = new PlayerEntity();
            player2.setPlayerId("player2");
            player2.setPlayerMoney(15000);
            playerRepository.save(player2);

            // 기본 주식 추가
            StockEntity stock1 = new StockEntity();
            stock1.setName("AAPL");
            stock1.setPrice(150);
            stockRepository.save(stock1);

            StockEntity stock2 = new StockEntity();
            stock2.setName("GOOGL");
            stock2.setPrice(2800);
            stockRepository.save(stock2);

            StockEntity stock3 = new StockEntity();
            stock3.setName("TSLA");
            stock3.setPrice(800);
            stockRepository.save(stock3);
        };
    }
}
