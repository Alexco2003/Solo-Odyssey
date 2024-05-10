package org.example.repositories;

import org.junit.jupiter.api.Test;
import org.example.models.Item;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BossMageRepositoryTest {

    private BossMageRepository bossMageRepository = new BossMageRepository();

    @Test
    void get() {
        assertNotNull(bossMageRepository.get(96));
        System.out.println();
        System.out.println(bossMageRepository.get(96));
    }

    @Test
    void getInventory() {
        assertNotNull(bossMageRepository.getInventory(96));
        System.out.println();
        ArrayList<Item> inventory = bossMageRepository.getInventory(96);
        for (Item item : inventory) {
            System.out.println(item);
        }

    }
}