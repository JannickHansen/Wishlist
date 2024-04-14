package com.example.wishlist.controller;

import ch.qos.logback.core.model.Model;
import com.example.wishlist.model.User;
import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.Bruger;
import com.example.wishlist.repository.DatabaseManager;
import com.example.wishlist.repository.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class wishListController {

    @Autowired
    WishList wishlist;
    @Autowired
    Bruger bruger;

    @GetMapping("/")
    public String forside() {
        return "Wishlistforside";
    }

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

    @GetMapping("/registrer")
    public String registrer() {
        return "registrer";
    }
    @PostMapping("/registrer")
    public String createUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        User user = new User(username, password);
        bruger.create(user);
        return "redirect:/registrer";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (DatabaseManager.authenticateUser(username, password)) {
            return "redirect:/create";
        } else {

            return "redirect:/loginError";
        }
    }
    @GetMapping("/loginError")
    public String loginError() {
        return "loginError";
    }
    @PostMapping("/loginError")
    public String loginError(@RequestParam String username, @RequestParam String password) {
        if (DatabaseManager.authenticateUser(username, password)) {
            return "redirect:/create";
        } else {

            return "redirect:/loginError";
        }
    }
}
