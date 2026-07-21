package com.daenini.coupon.dto;

import java.time.LocalDateTime;

public record CouponPolicyRequest (String name, int totalQuantity, LocalDateTime startAt, LocalDateTime endAt) {
}
