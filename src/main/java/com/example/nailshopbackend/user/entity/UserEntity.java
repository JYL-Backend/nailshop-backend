package com.example.nailshopbackend.user.entity;

import com.example.nailshopbackend.common.entity.BaseEntity;
import com.example.nailshopbackend.userauthority.entity.UserAuthorityEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<UserAuthorityEntity> authorities;
}
