package com.example.demo.coupon.service;

import com.example.demo.coupon.entity.Coupon;
import com.example.demo.coupon.entity.Event;
import com.example.demo.coupon.entity.EventCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {

    private final RedisTemplate<String,Object> redisTemplate;
    private static final long FIRST_ELEMENT = 0;
    private static final long LAST_ELEMENT = -1;
    private static final long PUBLISH_SIZE = 10;
    private static final long LAST_INDEX = 1;

    public List<EventCount> eventCountList = new ArrayList<>();

    public void setEventCount(Event event, int queue) {
        EventCount eventCount = new EventCount(event, queue);
        eventCountList.add(eventCount);
    }

    public void addQueue(Event event) {
        final String people = Thread.currentThread().getName();
        final long now = System.currentTimeMillis();

        for (EventCount eventCount : eventCountList) {
            if (eventCount.getEvent().getName().equals(event.getName())) {
                redisTemplate.opsForZSet().add(event.toString(), people, (int) now);

                log.info("대기열에 추가 - {} ({}초)", people, now);
                return;
            }
        }
    }

    public void getOrder(Event event) {
        for (EventCount eventCount : eventCountList) {
            if (eventCount.getEvent().getName().equals(event.getName())) {
                Set<Object> queue = redisTemplate.opsForZSet().range(event.toString(), FIRST_ELEMENT, LAST_ELEMENT);

                if (queue == null) {
                    log.info("'{}' 이벤트는 현재 진행중인 이벤트가 아닙니다.", event.getName());
                    return;
                }

                for (Object people : queue) {
                    Long rank = redisTemplate.opsForZSet().rank(event.toString(), people);
                    log.info("'{}'님의 현재 대기열은 {}명 남았습니다.", people, rank);
                }

                return;
            }
        }
    }

    public void publish(Event event) {
        for (EventCount eventCount : eventCountList) {
            if (eventCount.getEvent().getName().equals(event.getName())) {
                Set<Object> queue = redisTemplate.opsForZSet().range(event.toString(), FIRST_ELEMENT, PUBLISH_SIZE - LAST_INDEX);

                for (Object people : queue) {
                    final Coupon coupon = new Coupon(event);
                    log.info("'{}'님의 {} 쿠폰 발급되었습니다 ({})", people, coupon.getEvent().getName(), coupon.getCode());
                    redisTemplate.opsForZSet().remove(event.toString(), people);
                    eventCount.decrease();
                }
            }
        }
    }

    public boolean checkEvent(Event event) {
        for (EventCount eventCount : eventCountList) {
            if (eventCount.getEvent().getName().equals(event.getName())) {
                return true;
            }
        }

        return false;
    }

    public boolean validEnd(Event event) {
        for (EventCount eventCount : eventCountList) {
            if (eventCount.getEvent().getName().equals(event.getName())) {
                return eventCount.end();
            }
        }

        return false;
    }

    public long getSize(Event event) {
        for (EventCount eventCount : eventCountList) {
            if (eventCount.getEvent().getName().equals(event.getName())) {
                return redisTemplate.opsForZSet().size(event.toString());
            }
        }

        return -1;
    }
}
