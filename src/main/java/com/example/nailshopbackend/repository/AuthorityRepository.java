package com.example.nailshopbackend.repository;

import com.example.nailshopbackend.common.authority.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, String> {
}
