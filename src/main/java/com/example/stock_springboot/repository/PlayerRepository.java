package com.example.stock_springboot.repository;

import com.example.stock_springboot.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, String> { 
}
