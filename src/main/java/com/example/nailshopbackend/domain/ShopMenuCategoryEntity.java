package com.example.nailshopbackend.domain;

import com.example.nailshopbackend.common.entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;


/**
 * @FileName : ShopMenuCategoryEntity.java
 * @Date : 2022-01-24
 * @작성자 : jylim
 * @변경이력 :
 * @프로그램 설명 : 매장 메뉴 카테고리
 */

@Getter
@Entity
@Table(name="shop_menu_category")
public class ShopMenuCategoryEntity extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    @Column(name = "name")
    private String name;

    @Column(name = "sort")
    private int sort;
}
