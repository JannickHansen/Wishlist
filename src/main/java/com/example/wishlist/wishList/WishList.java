package com.example.wishlist.wishList;

import com.example.wishlist.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishList {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Wish wish) {
        final String INSERT_SQL ="INSERT INTO wish (wishListID, title, beskrivelse, billede, link) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(INSERT_SQL, wish.getWishListID(), wish.getTitle(), wish.getBeskrivelse(), wish.getBillede(), wish.getLink());
    }
}
