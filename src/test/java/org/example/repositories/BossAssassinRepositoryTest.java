package org.example.repositories;

import org.example.models.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BossAssassinRepositoryTest {

    private BossAssassinRepository bossAssassinRepository = new BossAssassinRepository();

    @Test
    void get() {
        assertNotNull(bossAssassinRepository.get(80));
        System.out.println();
        System.out.println(bossAssassinRepository.get(80));
    }

    @Test
    void getInventory() {
        assertNotNull(bossAssassinRepository.getInventory(80));
        System.out.println();
        ArrayList<Item> inventory = bossAssassinRepository.getInventory(80);
        for (Item item : inventory) {
            System.out.println(item);
        }
    }
}