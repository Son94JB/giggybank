package com.d208.giggyrank.controller;

import com.d208.giggyrank.dto.BeggerRankDto;
import com.d208.giggyrank.dto.BeggerRankResultDto;
import com.d208.giggyrank.service.HallOfBeggerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rank")
public class HallOfBeggerController {
    private final HallOfBeggerService hallOfBeggerService;

    @PostMapping("/hall-of-begger/update")
    public ResponseEntity<?> updateBeggerRank(@RequestBody BeggerRankDto beggerRankDto) {
        hallOfBeggerService.updateScore(beggerRankDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/hall-of-begger")
    public ResponseEntity<?> getTopRank(){
        List<BeggerRankResultDto> beggerRankResults = hallOfBeggerService.getTopRanking();
        return ResponseEntity.ok(beggerRankResults);
    }

    @PostMapping("/hall-of-begger")
    public ResponseEntity<?> getUserRank(@RequestBody String username){
        Long rank = hallOfBeggerService.getUserRank(username);
        return ResponseEntity.ok(rank);
    }
}
