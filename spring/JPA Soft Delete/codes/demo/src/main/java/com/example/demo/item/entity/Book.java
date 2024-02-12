package com.example.demo.item.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@OnDelete(action = OnDeleteAction.CASCADE)
@DiscriminatorValue("ALBUM")
public class Book extends Item {

    private String author;
    private String isbn;

    @Builder
    public Book(String name, String author, String isbn, int price) {
        setName(name);
        setPrice(price);
        this.author = author;
        this.isbn = isbn;
    }

    public static Book createBook(String name, int price, String author, String isbn) {
        return Book.builder()
                .name(name)
                .price(price)
                .author(author)
                .isbn(isbn)
                .build();
    }
}
