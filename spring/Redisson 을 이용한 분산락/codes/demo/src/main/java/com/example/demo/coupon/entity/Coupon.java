package com.example.demo.coupon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long count;

    public void useCoupon() {
        this.count -= 1;
    }

    @Builder
    public Coupon(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public static Coupon createCoupon(String name, Long count) {
        return Coupon.builder()
                .name(name)
                .count(count)
                .build();
    }
}
