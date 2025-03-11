package com.example.stock_springboot.controller;

import com.example.stock_springboot.dto.PlayerResponseDto;
import com.example.stock_springboot.entity.PlayerEntity;
import com.example.stock_springboot.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // 모든 플레이어 조회
    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    // ID로 플레이어 조회
    @GetMapping("/{id}")
    public ResponseEntity<PlayerEntity> getPlayerById(@PathVariable String id) {
        return playerService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 새로운 플레이어 추가
    @PostMapping
    public ResponseEntity<String> addPlayer(@RequestBody PlayerEntity player) {
        playerService.addPlayer(player);
        return ResponseEntity.ok("플레이어 추가 완료");
    }

    // 플레이어 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable String id) {
        boolean deleted = playerService.deletePlayer(id);
        return deleted ? ResponseEntity.ok("플레이어 삭제 완료")
                       : ResponseEntity.badRequest().body("존재하지 않는 플레이어입니다.");
    }
}
