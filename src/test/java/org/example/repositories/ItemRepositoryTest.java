package org.example.repositories;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRepositoryTest {

    private ItemRepository itemRepository = new ItemRepository();

    @Test
    @Order(1)
    void get() {
        assertNotNull(itemRepository.get(291));
        System.out.println();
        System.out.println(itemRepository.get(291));
    }

    @Test
    @Order(2)
    void updateItemQuantityOnBuy() {
        itemRepository.updateItemQuantityOnBuy(291);
        this.get();
    }

    @Test
    @Order(3)
    void updateItemQuantityOnSell() {
        itemRepository.updateItemQuantityOnSell(291);
        this.get();
    }

    @Test
    void countBoughtItems() {
        assertEquals(0, itemRepository.countBoughtItems(3));
    }
}