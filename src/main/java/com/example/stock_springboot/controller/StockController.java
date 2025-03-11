package com.example.stock_springboot.controller;

import com.example.stock_springboot.entity.StockEntity;
import com.example.stock_springboot.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // 모든 주식 목록 조회
    @GetMapping
    public ResponseEntity<List<StockEntity>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    // 특정 주식 가격 조회
    @GetMapping("/price/{symbol}")
    public ResponseEntity<String> getStockPrice(@PathVariable String symbol) {
        Double price = stockService.getStockPrice(symbol);
        return (price != null)
                ? ResponseEntity.ok("현재 " + symbol + " 주가: " + price + " USD.")
                : ResponseEntity.badRequest().body("주가 정보를 가져올 수 없습니다.");
    }

    // 새로운 주식 추가
    @PostMapping
    public ResponseEntity<String> addStock(@RequestBody StockEntity stock) {
        boolean success = stockService.addStock(stock.getName());
        return success ? ResponseEntity.ok("주식 추가 완료: " + stock.getName())
                       : ResponseEntity.badRequest().body("이미 존재하는 주식이거나 주가 정보를 가져올 수 없습니다.");
    }

    // 주식 삭제
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteStock(@PathVariable String name) {
        boolean success = stockService.deleteStock(name);
        return success ? ResponseEntity.ok("주식 삭제 완료") 
                       : ResponseEntity.badRequest().body("존재하지 않는 주식입니다.");
    }
}
