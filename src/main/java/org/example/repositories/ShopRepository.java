package org.example.repositories;

import org.example.models.Item;
import org.example.models.PlayerInventory;
import org.example.models.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ShopRepository implements GenericRepository<Shop> {

    public ShopRepository() {
    }

    // Basic CRUD operations
    @Override
    public void add(Shop shop) {

    }

    @Override
    public Shop get(int id) {
        String sql = "SELECT * FROM Shop WHERE id_shop = ?";
        Connection conn = this.databaseConnection.getConnection();
        Shop shop = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, PlayerInventory.class, "Done successfully!");

            while (rs.next()) {
                int shopId = rs.getInt("id_shop");
                int playerId = rs.getInt("id_player");
                String name = rs.getString("name");
                ArrayList<Item> shopItems = getShopItems(shopId);
                shop = new Shop(shopId, playerId, name, shopItems);
            }

            return shop;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

    @Override
    public ArrayList<Shop> getAll() {
        return null;
    }

    @Override
    public void update(Shop shop) {

    }

    @Override
    public void delete(Shop shop) {

    }

    // Functions related to the Shops' items
    public ArrayList<Item> getShopItems(int shopId) {
        String sql = "SELECT * FROM Item WHERE id_shop = ?";
        Connection conn = this.databaseConnection.getConnection();
        ArrayList<Item> shopItems = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, shopId);
            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, PlayerInventory.class, "Done successfully!");

            while (rs.next()) {
                int itemId = rs.getInt("id_item");
                int shopIdItem = rs.getInt("id_shop");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int damage = rs.getInt("damage");
                int health = rs.getInt("health");
                int quantity = rs.getInt("quantity");
                boolean isBought = rs.getBoolean("isBought");
                boolean isStolen = rs.getBoolean("isStolen");

                Item item = new Item(itemId, shopIdItem, name, description, price, damage, health, quantity, isBought, isStolen);
                shopItems.add(item);


            }

            return shopItems;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void buyItem(int playerId, int itemId) {
        String sql = "INSERT INTO PlayerInventory (id_player, id_item, quantity) VALUES (?, ?, 1) ON DUPLICATE KEY UPDATE quantity = quantity + 1";

        Connection conn = this.databaseConnection.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, itemId);
            pstmt.executeUpdate();
            this.auditDatabase.write(sql, PlayerInventory.class, "Done successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sellItem(int playerId, int itemId) {
        String sqlUpdate = "UPDATE PlayerInventory SET quantity = quantity - 1 WHERE id_player = ? AND id_item = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
            PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate);
            pstmtUpdate.setInt(1, playerId);
            pstmtUpdate.setInt(2, itemId);
            pstmtUpdate.executeUpdate();
            this.auditDatabase.write(sqlUpdate, PlayerInventory.class, "Done successfully!");

            String sqlCheck = "SELECT quantity FROM PlayerInventory WHERE id_player = ? AND id_item = ?";
            PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheck);
            pstmtCheck.setInt(1, playerId);
            pstmtCheck.setInt(2, itemId);
            ResultSet rs = pstmtCheck.executeQuery();
            if (rs.next() && rs.getInt("quantity") == 0) {
                String sqlDelete = "DELETE FROM PlayerInventory WHERE id_player = ? AND id_item = ?";
                PreparedStatement pstmtDelete = conn.prepareStatement(sqlDelete);
                pstmtDelete.setInt(1, playerId);
                pstmtDelete.setInt(2, itemId);
                pstmtDelete.executeUpdate();
                this.auditDatabase.write(sqlDelete, PlayerInventory.class, "Done successfully!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}
