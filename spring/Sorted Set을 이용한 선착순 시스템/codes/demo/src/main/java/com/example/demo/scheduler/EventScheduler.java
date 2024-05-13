package com.example.demo.scheduler;

import com.example.demo.coupon.entity.Event;
import com.example.demo.coupon.entity.EventCount;
import com.example.demo.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventScheduler {

    private final CouponService couponService;

    @Scheduled(fixedDelay = 1000)
    private void chickenEventScheduler() {
        for (EventCount eventCount : couponService.eventCountList) {
            Event event = eventCount.getEvent();

            if (couponService.checkEvent(event)) {
                if (!couponService.validEnd(event)) {
                    couponService.publish(event);
                    couponService.getOrder(event);
                } else {
                    log.info("===== '{}' 이벤트가 종료되었습니다. =====", event.getName());
                }
            }
        }
    }
}
