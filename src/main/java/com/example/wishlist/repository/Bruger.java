package com.example.wishlist.repository;

import com.example.wishlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Bruger {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(User user) {
        final String INSERT_SQL ="INSERT INTO wish (username, password) VALUES (?, ?)";
        jdbcTemplate.update(INSERT_SQL, user.getUsername(), user.getPassword());
    }
}
