package org.example.repositories;

import org.example.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BossTankRepository implements GenericRepository<BossTank> {
    @Override
    public void add(BossTank entity) {

    }

    @Override
    public BossTank get(int id) {
        BossTank bossTank = null;
        String sql = "SELECT e.id_enemy, e.name, e.description, e.health, e.damage, e.encountered, t.armor " +
                "FROM Enemy e " +
                "JOIN Tank t ON e.id_enemy = t.id_enemy " +
                "JOIN BossTank b ON t.id_enemy = b.id_enemy " +
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
                int armor = rs.getInt("armor");
                ArrayList<Item> inventory = getInventory(id_enemy);
                this.auditDatabase.write(sql, BossTank.class, "Done successfully!");
                bossTank = new BossTank(id_enemy, name, health, damage, armor, inventory, description, encountered);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bossTank;
    }

    @Override
    public ArrayList<BossTank> getAll() {
        return null;
    }

    @Override
    public void update(BossTank entity) {

    }

    @Override
    public void delete(BossTank entity) {

    }

    // Functions related to the inventory
    public ArrayList<Item> getInventory(int id) {
        String sql = "SELECT * FROM BossTankInventory WHERE id_enemy = ?";
        Connection conn = this.databaseConnection.getConnection();
        ArrayList<Item> inventory = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, BossTankInventory.class, "Done successfully!");

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
