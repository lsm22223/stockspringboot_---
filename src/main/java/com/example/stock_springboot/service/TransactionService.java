package com.example.stock_springboot.service;

import com.example.stock_springboot.entity.TransactionEntity;
import com.example.stock_springboot.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // 모든 거래 내역 조회
    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
