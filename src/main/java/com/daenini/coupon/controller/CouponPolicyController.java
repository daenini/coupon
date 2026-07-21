package com.daenini.coupon.controller;

import com.daenini.coupon.dto.CouponPolicyRequest;
import com.daenini.coupon.service.CouponPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponPolicyController {

    private final CouponPolicyService couponPolicyService;

    @PostMapping("/api/coupon-policies")
    public Long register(@RequestBody CouponPolicyRequest request) {

        Long id = couponPolicyService.register(request);

        return id;
    }
}
