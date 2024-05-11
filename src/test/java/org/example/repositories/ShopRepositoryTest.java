package org.example.repositories;

import org.example.models.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    private ShopRepository shopRepository = new ShopRepository();

    @Test
    void get() {
        assertNotNull(shopRepository.get(3));
        System.out.println();
        System.out.println(shopRepository.get(3));
    }

    @Test
    void getShopItems() {
        assertNotNull(shopRepository.getShopItems(3));
        System.out.println();
        ArrayList<Item> items = shopRepository.getShopItems(3);
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    void buyItem() {
        shopRepository.buyItem(3, 291);
    }

    @Test
    void sellItem() {
        shopRepository.sellItem(3, 291);
    }
}