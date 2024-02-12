package com.example.demo.item.service;

import com.example.demo.item.entity.Book;
import com.example.demo.item.entity.Item;
import com.example.demo.item.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.item.entity.Book.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @DisplayName("조회")
    public void searchItemTest() {
        //given
        Book book = itemRepository.save(createBook("데미안", 12000, "헤르만 헤세", "asdf-1234"));

        //when
        em.flush();
        em.clear();
        Item item = itemRepository.findById(book.getId())
                .orElseThrow(IllegalArgumentException::new);

        //then
        assertEquals(book.getName(), item.getName());
    }

    @Test
    @Transactional
    @DisplayName("soft delete 테스트")
    public void softDeleteTest() {
        //given
        Book book = itemRepository.save(createBook("데미안", 12000, "헤르만 헤세", "asdf-1234"));

        //when
        itemRepository.delete(book);

        em.flush();
        em.clear();

        //then
        assertThrows(IllegalArgumentException.class, () -> itemRepository.findById(book.getId())
                .orElseThrow(IllegalArgumentException::new));
    }
}