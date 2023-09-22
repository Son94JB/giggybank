package com.d208.giggyrank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HallOfBeggerService {
    public ResponseEntity<String> getHallOfBegger() {
        return ResponseEntity.ok("good");
    }
}
