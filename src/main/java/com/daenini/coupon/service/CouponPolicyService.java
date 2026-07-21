package com.daenini.coupon.service;

import com.daenini.coupon.domain.CouponPolicy;
import com.daenini.coupon.dto.CouponPolicyRequest;
import com.daenini.coupon.repository.CouponPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponPolicyService {

    private final CouponPolicyRepository couponPolicyRepository;

    @Transactional
    public Long register(CouponPolicyRequest request) {

        CouponPolicy policy = new CouponPolicy(request.name(), request.totalQuantity(), request.startAt(), request.endAt());

        CouponPolicy savedPolicy = couponPolicyRepository.save(policy);

        return savedPolicy.getId();
    }

}
