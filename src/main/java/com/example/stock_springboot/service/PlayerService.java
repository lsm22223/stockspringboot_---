package com.example.stock_springboot.service;

import com.example.stock_springboot.dto.PlayerResponseDto;
import com.example.stock_springboot.entity.PlayerEntity;
import com.example.stock_springboot.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    //조회 dto 적용
    public List<PlayerResponseDto> getAllPlayers() {
        List<PlayerEntity> playerEntity = playerRepository.findAll();
        List<PlayerResponseDto> playerResponseDto = playerEntity.stream()
                .map(PlayerResponseDto::new)
                .collect(Collectors.toList());
        return playerResponseDto;
    }

    public Optional<PlayerEntity> getPlayerById(String id) {
        return playerRepository.findById(id);
    }
    //플레이어 추가 
    public void addPlayer(PlayerEntity player) {
        playerRepository.save(player);
    }
    // 플레이어 삭제(보완必)
    public boolean deletePlayer(String id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
