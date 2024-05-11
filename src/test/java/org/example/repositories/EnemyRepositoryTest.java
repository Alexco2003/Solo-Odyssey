package org.example.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyRepositoryTest {

    private EnemyRepository enemyRepository = new EnemyRepository();

    @Test
    void updateEnemyEncountered() {
        enemyRepository.updateEnemyEncountered(75);
    }
}