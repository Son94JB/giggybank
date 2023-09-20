package com.d208.giggyapp.repository;

import com.d208.giggyapp.dto.User.TokenDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisRepository extends CrudRepository<TokenDto, String> {
}
