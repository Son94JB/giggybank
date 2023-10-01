package com.d208.giggyrank.repository;

import com.d208.giggyrank.domain.game.GameRank;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GameRankRepository extends CrudRepository<GameRank, UUID> {
}
