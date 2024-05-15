package org.example.repositories;

import org.example.models.Architect;
import org.example.models.Item;
import org.example.models.Player;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ArchitectRepository implements GenericRepository<Architect> {

    public ArchitectRepository() {
    }

    // Basic CRUD operations
    @Override
    public void add(Architect architect) {

        int userId = -1;
        String sql1 = "INSERT INTO USER (username, password) VALUES (?, ?)";
        Connection conn = this.databaseConnection.getConnection();

        try  {

            PreparedStatement pstmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, architect.getUsername());
            pstmt.setString(2, architect.getPassword());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                userId = rs.getInt(1);
            }
            this.auditDatabase.write(sql1, User.class, "Done successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        String sql = "INSERT INTO Architect (id_user, level) VALUES (?, ?)";


        try  {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userId);
            pstmt.setInt(2, architect.getLevel());

            pstmt.executeUpdate();
            this.auditDatabase.write(sql, Architect.class, "Done successfully!");
            this.auditSession.write("Architect " + architect.getUsername() + " has awaken! The System is pleased!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Architect get(int id) {
        String sql = "SELECT * FROM Architect INNER JOIN User ON Architect.id_user = User.id_user WHERE Architect.id_user = ?";
        Connection conn = this.databaseConnection.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, Architect.class, "Done successfully!");

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                int level = rs.getInt("level");
                return new Architect(id, username, password, level);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Architect> getAll() {
        String sql = "SELECT * FROM Architect INNER JOIN User ON Architect.id_user = User.id_user";
        Connection conn = this.databaseConnection.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, Architect.class, "Done successfully!");

            ArrayList<Architect> architects = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id_user");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int level = rs.getInt("level");
                architects.add(new Architect(id, username, password, level));

            }

            return architects;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    @Override
    public void update(Architect architect) {
        String sql = "UPDATE Architect SET level = ? WHERE id_user = ?";
        Connection conn = this.databaseConnection.getConnection();

        try  {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, architect.getLevel());
            pstmt.setInt(2, architect.getId_user());

            pstmt.executeUpdate();
            this.auditDatabase.write(sql, Architect.class, "Done successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Architect architect) {
        String sql = "DELETE FROM Architect WHERE id_user = ?";
        Connection conn = this.databaseConnection.getConnection();

        try  {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, architect.getId_user());

            pstmt.executeUpdate();
            this.auditDatabase.write(sql, Architect.class, "Done successfully!");
            this.auditSession.write("Architect " + architect.getUsername() + " has been terminated by The System!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // Function related to the login
    public boolean checkArchitectExists(int id) {
        String sql = "SELECT * FROM Architect WHERE id_user = ?";
        Connection conn = this.databaseConnection.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            this.auditDatabase.write(sql, Architect.class, "Done successfully!");

            return rs.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void promotePlayerToArchitect(int id) {
        String sql = "INSERT INTO Architect (id_user, level) VALUES (?, ?)";
        Connection conn = this.databaseConnection.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, 1000);
            pstmt.executeUpdate();
            this.auditDatabase.write(sql, Architect.class, "Done successfully!");
            this.auditSession.write("Player " + id + " has been promoted to Architect!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}
