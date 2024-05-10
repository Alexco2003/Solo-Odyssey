package org.example.repositories;

import org.example.models.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BossTankRepositoryTest {

    private BossTankRepository bossTankRepository = new BossTankRepository();

    @Test
    void get() {
        assertNotNull(bossTankRepository.get(88));
        System.out.println();
        System.out.println(bossTankRepository.get(88));
    }

    @Test
    void getInventory() {
        assertNotNull(bossTankRepository.getInventory(88));
        System.out.println();
        ArrayList<Item> inventory = bossTankRepository.getInventory(88);
        for (Item item : inventory) {
            System.out.println(item);
        }
    }
}