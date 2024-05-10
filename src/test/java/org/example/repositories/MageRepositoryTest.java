package org.example.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MageRepositoryTest {

    private MageRepository mageRepository = new MageRepository();

    @Test
    void get() {
        assertNotNull(mageRepository.get(91));
        System.out.println();
        System.out.println(mageRepository.get(91));

        assertNotNull(mageRepository.get(96));
        System.out.println();
        System.out.println(mageRepository.get(96));
    }
}