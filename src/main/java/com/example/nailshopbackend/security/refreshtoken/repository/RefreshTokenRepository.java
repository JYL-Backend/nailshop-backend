package com.example.nailshopbackend.security.refreshtoken.repository;

import com.example.nailshopbackend.security.refreshtoken.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, String> {
    Optional<RefreshTokenEntity> findById(String key);
}

