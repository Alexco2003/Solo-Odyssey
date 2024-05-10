package org.example.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TankRepositoryTest {

    private TankRepository tankRepository = new TankRepository();

    @Test
    void get() {
        assertNotNull(tankRepository.get(83));
        System.out.println();
        System.out.println(tankRepository.get(83));

        assertNotNull(tankRepository.get(88));
        System.out.println();
        System.out.println(tankRepository.get(88));
    }
}