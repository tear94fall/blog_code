package com.example.demo.coupon.service;

import com.example.demo.common.DistributedLock;
import com.example.demo.coupon.entity.Coupon;
import com.example.demo.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    @Transactional
    public Coupon saveCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Coupon findCoupon(Long id) {
        return couponRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @DistributedLock(key = "#id")
    public void useCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        coupon.useCoupon();
    }

    @Transactional
    public void useCouponNoLock(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        coupon.useCoupon();
    }
}
