package com.example.demo.item.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE item SET deleted = true where item_id = ?")
@SQLRestriction("deleted = false")
@DiscriminatorColumn
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Setter(AccessLevel.PROTECTED)
    private String name;

    @Setter(AccessLevel.PROTECTED)
    private int price;

    private boolean deleted = Boolean.FALSE;
}
