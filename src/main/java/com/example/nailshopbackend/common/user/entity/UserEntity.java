package com.example.nailshopbackend.common.user.entity;

import com.example.nailshopbackend.common.entity.BaseEntity;
import com.example.nailshopbackend.common.userauthority.entity.UserAuthorityEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    @Setter
    private String password;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<UserAuthorityEntity> authorities;
}
