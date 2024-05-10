package org.example.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssassinRepositoryTest {

    private AssassinRepository assassinRepository = new AssassinRepository();

    @Test
    void get() {
        assertNotNull(assassinRepository.get(75));
        System.out.println();
        System.out.println(assassinRepository.get(75));

        assertNotNull(assassinRepository.get(80));
        System.out.println();
        System.out.println(assassinRepository.get(80));
    }
}