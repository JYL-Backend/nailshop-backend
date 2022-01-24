package com.example.nailshopbackend.domain;

import com.example.nailshopbackend.common.entity.BaseEntity;
import com.example.nailshopbackend.common.user.entity.UserEntity;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @FileName : ShopEntity.java
 * @Date : 2022-01-24
 * @작성자 : jylim
 * @변경이력 :
 * @프로그램 설명 : 매장정보
 */
@Getter
@Entity
@Table(name = "shop")
public class ShopEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    UserEntity user;

    @Column(name = "name")
    private String name;

    @Column(name = "base_price")
    private int basePrice;

    @Column(name = "simple_address")
    private String simpleAddress;

    @Column(name = "address")
    private String address;

    @Column(name = "tel")
    private String tel;

    @Column(name = "open_time")
    private int openTime;

    @Column(name = "close_time")
    private int closeTime;

    @Column(name = "info")
    private String info;

    @Column(name = "rating")
    private int rating;

    @Column(name = "favorite_cout")
    private int favoriteCount;

    @Column(name = "comment_count")
    private int commentCount;

}
