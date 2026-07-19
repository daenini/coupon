package com.daenini.coupon.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class CouponIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private LocalDateTime issuedAt; // 발급 받은 날짜
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private CouponPolicy policy;

    public CouponIssue(String userId, LocalDateTime issuedAt, CouponPolicy policy) {
        this.userId = userId;
        this.issuedAt = issuedAt;
        this.policy = policy;
    }

    protected CouponIssue() {
    }

}
