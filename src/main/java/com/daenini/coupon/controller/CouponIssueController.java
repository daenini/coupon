package com.daenini.coupon.controller;

import com.daenini.coupon.dto.CouponIssueRequest;
import com.daenini.coupon.dto.CouponIssueResponse;
import com.daenini.coupon.service.CouponIssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CouponIssueController {

    private final CouponIssueService couponIssueService;

    @PostMapping("/api/coupon-policies/{policyId}/issues")
    public CouponIssueResponse issues(@PathVariable Long policyId, @RequestBody CouponIssueRequest request) {

        String userId = request.userId();

        return new CouponIssueResponse(couponIssueService.issue(policyId, userId));
    }

}
