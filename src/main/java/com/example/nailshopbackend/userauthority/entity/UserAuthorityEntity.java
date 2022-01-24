package com.example.nailshopbackend.userauthority.entity;

import com.example.nailshopbackend.authority.entity.AuthorityEntity;
import com.example.nailshopbackend.user.entity.UserEntity;
import com.example.nailshopbackend.userauthority.id.UserAuthorityID;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user_authority")
@IdClass(UserAuthorityID.class)
public class UserAuthorityEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private UserEntity user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_name")
    private AuthorityEntity authority;

}
