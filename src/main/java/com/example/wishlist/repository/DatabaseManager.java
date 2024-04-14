package com.example.wishlist.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class DatabaseManager {

    private static DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        DatabaseManager.dataSource = dataSource;
    }

    public static boolean authenticateUser(String username, String password) {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            return result.next(); // true if user exists with given credentials
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}