package com.example.wishlist.repository;

import com.example.wishlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class Bruger {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(User user) {
        final String INSERT_SQL = "INSERT INTO users (username, password) VALUES (?, ?)";
        jdbcTemplate.update(INSERT_SQL, user.getUsername(), user.getPassword());
    }

    public User findById(int userID) {

        final String FIND_BY_ID_SQL = "SELECT * FROM products WHERE id = ?";

        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

        return jdbcTemplate.queryForObject(FIND_BY_ID_SQL, rowMapper, userID);
    }
}
