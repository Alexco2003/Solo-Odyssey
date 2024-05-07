package org.example.repositories;

import org.example.models.PlayerInventory;

import java.util.ArrayList;

public class PlayerInventoryRepository implements GenericRepository<PlayerInventory> {

    public PlayerInventoryRepository() {
    }

    // Basic CRUD operations
    @Override
    public void add(PlayerInventory playerInventory) {

    }

    @Override
    public PlayerInventory get(int id) {
        return null;
    }

    @Override
    public ArrayList<PlayerInventory> getAll() {
        return null;
    }

    @Override
    public void update(PlayerInventory playerInventory) {

    }

    @Override
    public void delete(PlayerInventory playerInventory) {

    }
}
