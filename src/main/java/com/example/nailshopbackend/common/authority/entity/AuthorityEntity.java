package com.example.nailshopbackend.common.authority.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "authority")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityEntity {
    @Id
    @Column(name = "authority_name")
    private String authorityName;
}
