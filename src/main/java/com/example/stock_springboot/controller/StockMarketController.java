package com.example.stock_springboot.controller;

import com.example.stock_springboot.service.StockMarketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/market")
public class StockMarketController {
    private final StockMarketService stockMarketService;

    public StockMarketController(StockMarketService stockMarketService) {
        this.stockMarketService = stockMarketService;
    }

    // 주식 구매
    @PostMapping("/buy")
    public ResponseEntity<String> buyStock(@RequestParam String playerId,
                                           @RequestParam String stockName,
                                           @RequestParam int quantity) {
        boolean success = stockMarketService.buyStock(playerId, stockName, quantity);
        return success ? ResponseEntity.ok("주식 구매 성공")
                       : ResponseEntity.badRequest().body("주식 구매 실패: 금액 부족 또는 존재하지 않는 주식");
    }

    // 주식 판매
    @PostMapping("/sell")
    public ResponseEntity<String> sellStock(@RequestParam String playerId,
                                            @RequestParam String stockName,
                                            @RequestParam int quantity) {
        boolean success = stockMarketService.sellStock(playerId, stockName, quantity);
        return success ? ResponseEntity.ok("주식 판매 성공")
                       : ResponseEntity.badRequest().body("주식 판매 실패: 보유 주식 부족 또는 존재하지 않는 주식");
    }
}
