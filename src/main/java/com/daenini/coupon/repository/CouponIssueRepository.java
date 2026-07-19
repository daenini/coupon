package com.daenini.coupon.repository;

import com.daenini.coupon.domain.CouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponIssueRepository extends JpaRepository<CouponIssue,Long> {
}
