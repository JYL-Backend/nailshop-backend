package com.example.nailshopbackend.authority.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "authority")
public class AuthorityEntity {
    @Id
    @Column(name = "authority_name")
    private String authorityName;
}
