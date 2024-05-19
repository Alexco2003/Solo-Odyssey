package org.example.repositories;

import org.example.models.Item;
import org.example.models.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PlayerRepositoryTest {

    private PlayerRepository playerRepository = new PlayerRepository();

    @Test
    void get() {
        assertNotNull(playerRepository.get(3));
        System.out.println();
        System.out.println(playerRepository.get(3));
    }

    @Test
    void getAll() {
        assertNotNull(playerRepository.getAll());
        System.out.println();
        ArrayList<Player> players = playerRepository.getAll();
        for (Player player : players) {
            System.out.println(player);
        }

    }

    @Test
    void checkLogin() {
        assertEquals(3, playerRepository.checkLogin("Andrei", "ANDREI"));
    }

    @Test
    void checkPlayerExists() {
        assertTrue(playerRepository.checkPlayerExists(3));
        assertFalse(playerRepository.checkPlayerExists(0));
    }

    @Test
    void checkUsernameExists() {
        assertTrue(playerRepository.checkUsernameExists("Alexco"));
        assertFalse(playerRepository.checkUsernameExists(""));
    }

    @Test
    void getInventory() {
        assertNotNull(playerRepository.getInventory(3));
        System.out.println();
        HashMap<Item,Integer> inventory = playerRepository.getInventory(3);
for (Item item : inventory.keySet()) {
            System.out.println(item + " " + inventory.get(item));
        }
    }

    @Test
    void updateMoneyOnBuy() {
        this.playerRepository.updateMoneyOnBuy(3, 100);

    }

    @Test
    void updateDamageHealthOnBuy() {
        this.playerRepository.updateDamageHealthOnBuy(3, 10, 10);
    }

    @Test
    void updateMoneyOnSell() {
        this.playerRepository.updateMoneyOnSell(3, 100);
    }

    @Test
    void updateDamageHealthOnSell() {
        this.playerRepository.updateDamageHealthOnSell(3, 10, 10);
    }
}