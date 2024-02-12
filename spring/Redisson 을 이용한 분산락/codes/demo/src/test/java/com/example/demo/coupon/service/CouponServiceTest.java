package com.example.demo.coupon.service;

import com.example.demo.coupon.entity.Coupon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.demo.coupon.entity.Coupon.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CouponServiceTest {

    @Autowired
    CouponService couponService;

    @Test
    @DisplayName("쿠폰 소진 분산락 테스트")
    public void useCouponTest() throws InterruptedException {
        //given
        Coupon coupon = couponService.saveCoupon(createCoupon("할인 쿠폰", 100L));

        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    couponService.useCoupon(coupon.getId());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        //when
        Coupon findCoupon = couponService.findCoupon(coupon.getId());

        //then
        assertEquals(findCoupon.getCount(), 0);
        System.out.println("잔여 쿠폰 개수 = " + findCoupon.getCount());
    }

    @Test
    @DisplayName("쿠폰 소진 테스트")
    public void noLockUseCouponTest() throws InterruptedException {
        //given
        Coupon coupon = couponService.saveCoupon(createCoupon("할인 쿠폰", 100L));

        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    couponService.useCouponNoLock(coupon.getId());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        //when
        Coupon findCoupon = couponService.findCoupon(coupon.getId());

        //then
        assertNotEquals(findCoupon.getCount(), 0);
        System.out.println("잔여 쿠폰 개수 = " + findCoupon.getCount());
    }
}