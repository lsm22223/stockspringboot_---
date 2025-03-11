package com.example.stock_springboot.controller;

import com.example.stock_springboot.entity.TransactionEntity;
import com.example.stock_springboot.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // 모든 거래 내역 조회
    @GetMapping
    public ResponseEntity<List<TransactionEntity>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}
