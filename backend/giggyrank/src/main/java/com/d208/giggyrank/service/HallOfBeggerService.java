package com.d208.giggyrank.service;

import com.d208.giggyrank.repository.HallOfBeggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HallOfBeggerService {

    private final HallOfBeggerRepository hallOfBeggerRepository;

    public ResponseEntity<String> getHallOfBegger() {
        return ResponseEntity.ok("good");
    }
}
