package com.example.nailshopbackend.domain;

import com.example.nailshopbackend.common.entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

/**
 * @FileName : MenuAddInfoEntity.java
 * @Date : 2022-01-24
 * @작성자 : jylim
 * @변경이력 :
 * @프로그램 설명 : 메뉴 부가 정보
 */

@Getter
@Entity
@Table(name = "menu_add_info")
public class MenuAddInfoEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private ShopMenuEntity menu;


    @Column(name = "title")
    private int title;

    @Column(name = "content")
    private int content;
}
