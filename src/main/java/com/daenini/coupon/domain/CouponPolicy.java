package com.daenini.coupon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class CouponPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk
    private String name; // 정책 이름
    private int totalQuantity; // 총 발급 한도
    private int issuedQuantity; // 현재까지 발금된 개수 - 동시성 이슈가 발생하는 필드
    private LocalDateTime startAt; // 발급가능 기간 시작
    private LocalDateTime endAt; // 발급 가능 기간 종료

    public CouponPolicy(String name, int totalQuantity, LocalDateTime startAt, LocalDateTime endAt) {
        this.endAt = endAt;
        this.startAt = startAt;
        this.totalQuantity = totalQuantity;
        this.name = name;
        this.issuedQuantity = 0;
    }

    protected CouponPolicy() {}

    public void issue(LocalDateTime now) {
        if (now.isBefore(this.startAt) || !now.isBefore(this.endAt)) {
            throw new IllegalStateException("발급 가능 기간이 아닙니다.");
        }
        if (this.issuedQuantity >= this.totalQuantity) {
            throw new IllegalStateException("쿠폰이 모두 소진되었습니다.");
        }
        this.issuedQuantity++;
    }
}
