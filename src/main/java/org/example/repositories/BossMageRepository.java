package org.example.repositories;

import org.example.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BossMageRepository implements GenericRepository<BossMage>{
    @Override
    public void add(BossMage entity) {

    }

    @Override
    public BossMage get(int id) {
        BossMage bossMage = null;
        String sql = "SELECT e.id_enemy, e.name, e.description, e.health, e.damage, e.encountered, m.mana " +
                "FROM Enemy e " +
                "JOIN Mage m ON e.id_enemy = m.id_enemy " +
                "JOIN BossMage b ON m.id_enemy = b.id_enemy " +
                "WHERE e.id_enemy = ?";
        Connection conn = this.databaseConnection.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id_enemy = rs.getInt("id_enemy");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int health = rs.getInt("health");
                int damage = rs.getInt("damage");
                boolean encountered = rs.getBoolean("encountered");
                int mana = rs.getInt("mana");
                ArrayList<Item> inventory = getInventory(id_enemy);
                this.auditDatabase.write(sql, BossMage.class, "Done successfully!");
                bossMage = new BossMage(id_enemy, name, health, damage, mana, inventory, description, encountered);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bossMage;
    }

    @Override
    public ArrayList<BossMage> getAll() {
        return null;
    }

    @Override
    public void update(BossMage entity) {

    }

    @Override
    public void delete(BossMage entity) {

    }

    // Functions related to the inventory
    public ArrayList<Item> getInventory(int id) {
        String sql = "SELECT * FROM BossMageInventory WHERE id_enemy = ?";
        Connection conn = this.databaseConnection.getConnection();
        ArrayList<Item> inventory = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, BossMageInventory.class, "Done successfully!");

            while (rs.next()) {
                int itemId = rs.getInt("id_item");

                Item item = getItem(itemId);
                if (item != null) {
                    inventory.add(item);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return inventory;
    }

    private Item getItem(int itemId) {
        String sql = "SELECT * FROM Item WHERE id_item = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, Item.class, "Done successfully!");

            if (rs.next()) {
                return new Item(rs.getInt("id_item"), rs.getInt("id_shop"), rs.getString("name"),rs.getString("description"), rs.getDouble("price"), rs.getInt("damage"), rs.getInt("health"), rs.getInt("quantity"), rs.getBoolean("isBought"), rs.getBoolean("isStolen"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
