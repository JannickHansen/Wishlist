package com.example.wishlist.repository;

import com.example.wishlist.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishList {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        WishList.dataSource = dataSource;
    }

    public void create(Wish wish) {
        final String INSERT_SQL ="INSERT INTO wish (wishListID, title, beskrivelse, billede, link) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(INSERT_SQL, wish.getWishListID(), wish.getTitle(), wish.getBeskrivelse(), wish.getBillede());
    }

    public List<Wish> loadWishList(int wishListID) {
        List<Wish> wishes = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM wish WHERE wishListID = ? ORDER BY wishID";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, wishListID);

                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        Wish wish = new Wish();
                        wish.setWishID(resultSet.getInt("wishID"));
                        wish.setWishListID(resultSet.getInt("wishListID"));
                        wish.setTitle(resultSet.getString("title"));
                        wish.setBeskrivelse(resultSet.getString("beskrivelse"));
                        wish.setBillede(resultSet.getString("billede"));

                        wishes.add(wish);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishes;
    }
}
