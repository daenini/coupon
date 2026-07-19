package com.daenini.coupon.service;

import com.daenini.coupon.domain.CouponIssue;
import com.daenini.coupon.domain.CouponPolicy;
import com.daenini.coupon.repository.CouponIssueRepository;
import com.daenini.coupon.repository.CouponPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CouponIssueService {

    private final CouponPolicyRepository couponPolicyRepository;
    private final CouponIssueRepository couponIssueRepository;

    @Transactional
    public Long issue(Long policyId, String userId) {
        // 1. 정책 조회 (없으면 예외)
        CouponPolicy policy = couponPolicyRepository.findById(policyId)
                .orElseThrow(() -> new IllegalArgumentException("CouponPolicy not found"));

        LocalDateTime now = LocalDateTime.now();

        // 2. 발급 (재고 차감)
        policy.issue(now);

        // 3. 발급 내역 생성 + 저장
        CouponIssue couponIssue = new CouponIssue(userId,now,policy);
        couponIssueRepository.save(couponIssue);

        return couponIssue.getId();
    }
}