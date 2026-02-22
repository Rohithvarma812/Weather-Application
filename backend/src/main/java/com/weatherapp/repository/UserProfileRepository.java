package com.weatherapp.repository;

import com.weatherapp.entity.UserProfile;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserProfileRepository extends R2dbcRepository<UserProfile, Long> {
    Mono<UserProfile> findByUsername(String username);
}
