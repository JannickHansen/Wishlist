package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.Bruger;
import com.example.wishlist.repository.DatabaseManager;
import com.example.wishlist.repository.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class wishListController {

    @Autowired
    WishList wishlist;
    @Autowired
    Bruger bruger;
    @Autowired
    private WishList wishList;

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
            @RequestParam("id") int wishListID,
            @RequestParam("title") String title,
            @RequestParam("beskrivelse") String beskrivelse,
            @RequestParam("billede") String billede
    ) {
        Wish wish = new Wish(wishListID, title, beskrivelse, billede);
        wishlist.create(wish);
        return "redirect:/wishlist?id=" + wishListID;
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
        int wishListID = DatabaseManager.authenticateUser(username, password);
        if (wishListID != -1) {
            return "redirect:/wishlist?id=" + wishListID;
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
        int wishListID = DatabaseManager.authenticateUser(username, password);
        if (wishListID != -1) {
            return "redirect:/wishlist?id=" + wishListID;
        } else {

            return "redirect:/loginError";
        }
    }
    @GetMapping("/wishlist")
    public String showWishlist(@RequestParam("id") int wishListID, Model model) {
        List<Wish> wishes = wishList.loadWishList(wishListID);
        model.addAttribute("wishes", wishes);
        model.addAttribute("id", wishListID);
        return "wishlist";
    }

}
