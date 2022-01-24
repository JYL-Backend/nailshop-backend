package com.example.nailshopbackend.domain;

import com.example.nailshopbackend.common.entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

/**
 * @FileName : ShopMenuEntity.java
 * @Date : 2022-01-24
 * @작성자 : jylim
 * @변경이력 :
 * @프로그램 설명 : 매장 메뉴
 */
@Getter
@Entity
@Table(name = "shop_menu")
public class ShopMenuEntity extends BaseEntity {
    @Id @GeneratedValue

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ShopMenuCategoryEntity category;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "discount_price")
    private int discountPrice;

    @Column(name = "info")
    private String info;


    @Column(name = "img_src")
    private String imgSrc;

    @Column(name = "favorite_count")
    private int favoriteCount;

    @Column(name = "review_count")
    private int reviewCount;

    @Column(name = "current_time_minute")
    private int currentTimeMinute;

}
