package com.example.nailshopbackend.common.user.repository;

import com.example.nailshopbackend.common.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
