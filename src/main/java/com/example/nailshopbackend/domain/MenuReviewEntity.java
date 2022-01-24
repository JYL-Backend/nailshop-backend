package com.example.nailshopbackend.domain;

import com.example.nailshopbackend.common.entity.BaseEntity;
import com.example.nailshopbackend.common.user.entity.UserEntity;
import lombok.Getter;

import javax.persistence.*;

/**
 * @FileName : MenuReviewEntity.java
 * @Date : 2022-01-24
 * @작성자 : jylim
 * @변경이력 :
 * @프로그램 설명 : 메뉴 리뷰
 */

@Getter
@Entity
@Table(name = "menu_review")
public class MenuReviewEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register")
    private UserEntity register;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private ShopMenuEntity menuEntity;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private int rating;

    @Column(name = "img_src")
    private String imgSrc;

    @Column(name = "reservation_count")
    private int reservationCount;


}
