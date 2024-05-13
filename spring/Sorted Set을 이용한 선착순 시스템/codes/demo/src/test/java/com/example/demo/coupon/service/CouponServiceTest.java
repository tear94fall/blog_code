package com.example.demo.coupon.service;

import com.example.demo.coupon.entity.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Test
    @DisplayName("아이스크림 선착순 쿠폰 이벤트 테스트")
    void iceCreamCouponTest() throws InterruptedException {
        // ice cream
        final Event event = Event.ICE_CREAM;
        final int userCount = 100;
        final int limit = 30;

        final CountDownLatch countDownLatch = new CountDownLatch(userCount);

        couponService.setEventCount(event, limit);

        List<Thread> workers = Stream
                .generate(() -> new Thread(new AddQueueWorker(countDownLatch, event)))
                .limit(userCount)
                .toList();

        workers.forEach(Thread::start);
        countDownLatch.await();

        Thread.sleep(5000); // scheduler run 5 times

        final long failEventPeople = couponService.getSize(event);

        assertEquals(userCount - limit, failEventPeople);
    }

    @Test
    @DisplayName("피자 선착순 쿠폰 이벤트 테스트")
    void pizzaCouponTest() throws InterruptedException {

        // pizza
        final Event event = Event.PIZZA;
        final int userCount = 100;
        final int limit = 30;

        final CountDownLatch countDownLatch = new CountDownLatch(userCount);

        couponService.setEventCount(event, limit);

        List<Thread> workers = Stream
                .generate(() -> new Thread(new AddQueueWorker(countDownLatch, event)))
                .limit(userCount)
                .toList();

        workers.forEach(Thread::start);
        countDownLatch.await();

        Thread.sleep(5000); // scheduler run 5 times

        final long failEventPeople = couponService.getSize(event);

        assertEquals(userCount - limit, failEventPeople);
    }

    private class AddQueueWorker implements Runnable{
        private CountDownLatch countDownLatch;
        private Event event;

        public AddQueueWorker(CountDownLatch countDownLatch, Event event) {
            this.countDownLatch = countDownLatch;
            this.event = event;
        }

        @Override
        public void run() {
            couponService.addQueue(event);
            countDownLatch.countDown();
        }
    }
}