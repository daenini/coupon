package com.daenini.coupon.dto;

import java.time.LocalDateTime;

public record CouponPolicyResponse (Long id, String name, int totalQuantity, int issuedQuantity ,LocalDateTime startAt, LocalDateTime endAt) {
}
