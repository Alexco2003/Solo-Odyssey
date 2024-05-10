package org.example.repositories;

import org.example.models.BossAssassin;
import org.example.models.BossAssassinInventory;
import org.example.models.Item;
import org.example.models.PlayerInventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class BossAssassinRepository implements GenericRepository<BossAssassin>{
    @Override
    public void add(BossAssassin entity) {

    }

    @Override
    public BossAssassin get(int id) {
        BossAssassin bossAssassin = null;
        String sql = "SELECT e.id_enemy, e.name, e.description, e.health, e.damage, e.encountered, a.criticalChance " +
                "FROM Enemy e " +
                "JOIN Assassin a ON e.id_enemy = a.id_enemy " +
                "JOIN BossAssassin b ON a.id_enemy = b.id_enemy " +
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
                int criticalChance = rs.getInt("criticalChance");
                ArrayList<Item> inventory = getInventory(id_enemy);
                this.auditDatabase.write(sql, BossAssassin.class, "Done successfully!");
                bossAssassin = new BossAssassin(id_enemy, name, health, damage, criticalChance, inventory, description, encountered);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bossAssassin;
    }

    @Override
    public ArrayList<BossAssassin> getAll() {
        return null;
    }

    @Override
    public void update(BossAssassin entity) {

    }

    @Override
    public void delete(BossAssassin entity) {

    }

    // Functions related to the inventory
    public ArrayList<Item> getInventory(int id) {
        String sql = "SELECT * FROM BossAssassinInventory WHERE id_enemy = ?";
        Connection conn = this.databaseConnection.getConnection();
        ArrayList<Item> inventory = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, BossAssassinInventory.class, "Done successfully!");

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
