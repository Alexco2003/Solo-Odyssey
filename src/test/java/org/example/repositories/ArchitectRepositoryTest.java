package org.example.repositories;

import org.example.models.Architect;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArchitectRepositoryTest {

    private ArchitectRepository architectRepository = new ArchitectRepository();

    @Test
    void get() {
        assertNotNull(architectRepository.get(1));
        System.out.println();
        System.out.println(architectRepository.get(1));
    }

    @Test
    void getAll() {
        assertNotNull(architectRepository.getAll());
        System.out.println();
        ArrayList<Architect> architects = architectRepository.getAll();
        for (Architect architect : architects) {
            System.out.println(architect);
        }

    }

    @Test
    void checkArchitectExists() {
        assertTrue(architectRepository.checkArchitectExists(1));
        assertFalse(architectRepository.checkArchitectExists(0));
    }
}