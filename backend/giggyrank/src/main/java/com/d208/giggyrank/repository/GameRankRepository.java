package com.d208.giggyrank.repository;

import com.d208.giggyrank.domain.game.GameRank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRankRepository extends CrudRepository<GameRank, Long> {
    Optional<GameRank> findByUserId(UUID userId);

}
