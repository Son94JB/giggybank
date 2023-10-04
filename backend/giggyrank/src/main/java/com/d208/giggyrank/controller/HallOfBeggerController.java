package com.d208.giggyrank.controller;

import com.d208.giggyrank.service.HallOfBeggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rank")
public class HallOfBeggerController {
    private final HallOfBeggerService hallOfBeggerService;

    @GetMapping("/hall-of-begger")
    public ResponseEntity<String> getHallOfBegger() {
        return hallOfBeggerService.getHallOfBegger();
    }

}
