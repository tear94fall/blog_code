package com.example.demo.coupon.entity;

import lombok.Getter;

@Getter
public enum Event {
    ICE_CREAM("ice-cream"),
    PIZZA("pizza");

    private final String name;

    Event(String name) {
        this.name = name;
    }
}
