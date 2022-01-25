package com.example.nailshopbackend.repository;

import com.example.nailshopbackend.common.userauthority.entity.UserAuthorityEntity;
import com.example.nailshopbackend.common.userauthority.id.UserAuthorityID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthorityEntity, UserAuthorityID>{
}
