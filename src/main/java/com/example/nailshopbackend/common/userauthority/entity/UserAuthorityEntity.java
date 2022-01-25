package com.example.nailshopbackend.common.userauthority.entity;

import com.example.nailshopbackend.common.authority.entity.AuthorityEntity;
import com.example.nailshopbackend.common.user.entity.UserEntity;
import com.example.nailshopbackend.common.userauthority.id.UserAuthorityID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user_authority")
@IdClass(UserAuthorityID.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
