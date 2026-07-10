package com.daenini.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class CouponPolicyTest {

    // 정상 발급 / 시작 전 / 종료 후 / 재고 소진 / startAt 정각(경계값).

    @Test
    @DisplayName("발급 가능 기간 내이고 재고가 남아있으면 발급에 성공하고 발급 수량이 1 증가한다")
    void issue_success() {
        // given
        LocalDateTime startAt = LocalDateTime.of(2026, 7, 1, 10, 0);
        LocalDateTime endAt = LocalDateTime.of(2026, 7, 31, 10, 0);
        CouponPolicy policy = new CouponPolicy("선착순 치킨 쿠폰", 100, startAt, endAt);

        // when
        policy.issue(LocalDateTime.of(2026, 7, 15, 12, 0));

        // then
        assertThat(policy.getIssuedQuantity()).isEqualTo(1);
    }

    @Test
    @DisplayName("발급 시작 시각 이전에는 발급할 수 없다")
    void issue_beforeStartAt_fail() {
        //given
        LocalDateTime startAt = LocalDateTime.of(2026, 7, 11, 10, 0);
        LocalDateTime endAt = LocalDateTime.of(2026, 7, 31, 10, 0);
        CouponPolicy policy = new CouponPolicy("선착순 치킨 쿠폰", 100, startAt, endAt);

        // when & then
        assertThatThrownBy(() -> policy.issue(LocalDateTime.of(2026, 7, 10, 12, 0)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("발급 가능 기간이 아닙니다.");

    }


    @Test
    @DisplayName("발급 종료 시각 이후에는 발급할 수 없다")
    void issue_afterEndAt_fail() {
        //given
        LocalDateTime startAt = LocalDateTime.of(2026, 7, 11, 10, 0);
        LocalDateTime endAt = LocalDateTime.of(2026, 7, 31, 10, 0);
        CouponPolicy policy = new CouponPolicy("선착순 치킨 쿠폰", 100, startAt, endAt);

        // when & then
        assertThatThrownBy(() -> policy.issue(LocalDateTime.of(2026, 7, 31, 10, 0)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("발급 가능 기간이 아닙니다.");
    }


    @Test
    @DisplayName("재고가 모두 소진되면 더 이상 발급할 수 없다")
    void issue_soldOut_fail() {
        //given
        LocalDateTime startAt = LocalDateTime.of(2026, 7, 11, 10, 0);
        LocalDateTime endAt = LocalDateTime.of(2026, 7, 31, 10, 0);
        CouponPolicy policy = new CouponPolicy("선착순 치킨 쿠폰", 1, startAt, endAt);
        policy.issue(LocalDateTime.of(2026, 7, 15, 12, 0));

        // when & then
        assertThatThrownBy(() -> policy.issue(LocalDateTime.of(2026, 7, 23, 12, 0)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("쿠폰이 모두 소진되었습니다.");
    }


    @Test
    @DisplayName("발급 시작 시각 정각에는 발급에 성공한다")
    void issue_atStartAt_success() {
        //given
        LocalDateTime startAt = LocalDateTime.of(2026, 7, 11, 10, 0);
        LocalDateTime endAt = LocalDateTime.of(2026, 7, 31, 10, 0);
        CouponPolicy policy = new CouponPolicy("선착순 치킨 쿠폰", 100, startAt, endAt);

        // when
        policy.issue(LocalDateTime.of(2026, 7, 11, 10, 0));

        // then
        assertThat(policy.getIssuedQuantity()).isEqualTo(1);


    }


}