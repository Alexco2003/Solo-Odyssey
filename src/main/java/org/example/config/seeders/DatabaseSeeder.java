package org.example.config.seeders;

import org.example.models.Architect;
import org.example.models.User;
import org.example.audit.AuditDatabase;
import org.example.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseSeeder {

    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    AuditDatabase auditDatabase = AuditDatabase.getInstance();

    public void seed() {
        Connection connection = this.databaseConnection.getConnection();
        String checkSql = "SELECT COUNT(*) FROM User";
        try {
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                String sql = "INSERT INTO User (id_user, username, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, "The Architect");
                preparedStatement.setString(3, "2003");
                preparedStatement.executeUpdate();
                this.auditDatabase.write(sql, User.class, "Done successfully!");

                String sql1 = "INSERT INTO Architect (id_user, level) VALUES (?, ?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setInt(1, 1);
                preparedStatement1.setInt(2, 1000);
                preparedStatement1.executeUpdate();
                this.auditDatabase.write(sql1, Architect.class, "Done successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}