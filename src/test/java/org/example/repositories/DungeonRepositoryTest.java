package org.example.repositories;

import org.example.models.Dungeon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DungeonRepositoryTest {

    private DungeonRepository dungeonRepository = new DungeonRepository();

    @Test
    void getDungeonByPlayerId() {
        assertNotNull(dungeonRepository.getDungeonByPlayerId(3));
        System.out.println();
        ArrayList<Dungeon> dungeons = dungeonRepository.getDungeonByPlayerId(3);
        for (Dungeon dungeon : dungeons) {
            System.out.println(dungeon);
        }

    }

    @Test
    void getEnemiesByDungeonId() {
        assertNotNull(dungeonRepository.getEnemiesByDungeonId(30));
        System.out.println();
        ArrayList<Integer> enemies = dungeonRepository.getEnemiesByDungeonId(30);
        for (Integer enemy : enemies) {
            System.out.println(enemy);
        }
    }

    @Test
    void updateDungeonCompleted() {
        dungeonRepository.updateDungeonCompleted(30);
    }

    @Test
    void countDungeonsCompleted() {
        assertEquals(1, dungeonRepository.countDungeonsCompleted(3));
    }

    @Test
    void testCountDungeonsCompleted() {
        assertEquals(1, dungeonRepository.countDungeonsCompleted(3));
    }
}