package com.example.nailshopbackend.domain;

import com.example.nailshopbackend.common.entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

/**
 * @FileName : ShopImageEntity.java
 * @Date : 2022-01-24
 * @작성자 : jylim
 * @변경이력 :
 * @프로그램 설명 : 매장 이미지
 */
@Getter
@Entity
@Table(name = "shop_image")
public class ShopImageEntity extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    ShopEntity shop;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "sort")
    private int sort;
}
