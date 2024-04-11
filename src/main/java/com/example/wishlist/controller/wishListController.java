package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.wishList.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class wishListController {

    @Autowired
    WishList wishlist;

    @GetMapping("/create")
    public String create(){
        return "create";
    }

    @PostMapping("/create")
    public String createWish(
            @RequestParam("wishListID") int wishListID,
            @RequestParam("title") String title,
            @RequestParam("beskrivelse") String beskrivelse,
            @RequestParam("billede") String billede,
            @RequestParam("link") String link
    ) {
        Wish wish = new Wish(wishListID, title, beskrivelse, billede, link);
        wishlist.create(wish);
        return "redirect:/create";
    }
}
