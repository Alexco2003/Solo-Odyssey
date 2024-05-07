package org.example.repositories;

import org.example.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemRepository implements GenericRepository<Item> {

    public ItemRepository() {
    }

    // Basic CRUD operations
    @Override
    public void add(Item item) {

    }

    @Override
    public Item get(int id) {
        String sql = "SELECT * FROM Item WHERE id_item = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
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

    @Override
    public ArrayList<Item> getAll() {
        return null;
    }

    @Override
    public void update(Item item) {

    }

    @Override
    public void delete(Item item) {

    }

    public void updateItemQuantityOnBuy(int itemId) {
        String sql = "UPDATE Item SET quantity = quantity - 1 WHERE id_item = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, itemId);

            pstmt.executeUpdate();
            this.auditDatabase.write(sql, Item.class, "Done successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
