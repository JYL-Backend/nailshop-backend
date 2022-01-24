package com.example.nailshopbackend.domain;

import lombok.Getter;

import javax.persistence.*;

/**
 * @FileName : ShopReviewCommentEntity.java
 * @Date : 2022-01-24
 * @작성자 : jylim
 * @변경이력 :
 * @프로그램 설명 : 가게 리뷰 댓글
 */

@Getter
@Entity
@Table(name = "shop_review_comment")
public class ShopReviewCommentEntity {
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_review_id")
    MenuReviewEntity menuReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    ShopEntity shop;

    @Column(name = "comment")
    private String comment;
}
