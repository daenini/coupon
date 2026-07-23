package com.daenini.coupon.controller;

import com.daenini.coupon.dto.CouponPolicyRequest;
import com.daenini.coupon.dto.CouponPolicyResponse;
import com.daenini.coupon.service.CouponPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CouponPolicyController {

    private final CouponPolicyService couponPolicyService;

    @PostMapping("/api/coupon-policies")
    public Long register(@RequestBody CouponPolicyRequest request) {

        Long id = couponPolicyService.register(request);

        return id;
    }

    @GetMapping("/api/coupon-policies/{id}")
    public CouponPolicyResponse getPolicy(@PathVariable Long id) {

        CouponPolicyResponse response = couponPolicyService.getPolicy(id);

        return response;
    }
}
