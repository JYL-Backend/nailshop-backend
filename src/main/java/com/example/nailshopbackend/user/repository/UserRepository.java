package com.example.nailshopbackend.user.repository;

import com.example.nailshopbackend.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
