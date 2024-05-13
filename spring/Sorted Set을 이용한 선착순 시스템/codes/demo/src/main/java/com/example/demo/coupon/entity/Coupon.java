package com.example.demo.coupon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Coupon {

    private Event event;
    private String code;

    public Coupon(Event event) {
        this.event = event;
        this.code = UUID.randomUUID().toString();
    }
}
