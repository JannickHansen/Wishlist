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
import org.springframework.web.bind.annotation.PathVariable;
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
    public String create(@RequestParam("wishListID") int wishListID, Model model){
        model.addAttribute("wishListID", wishListID);
        return "create";
    }

    @PostMapping("/create")
    public String createWish(
            @RequestParam("wishListID") Integer wishListID,
            @RequestParam("title") String title,
            @RequestParam("beskrivelse") String beskrivelse,
            @RequestParam("link") String link
    ) {
        Wish wish = new Wish(wishListID, title, beskrivelse, link);
        wishlist.create(wish);
        return "redirect:/wishlist?wishListID=" + wishListID;
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
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        int wishListID = DatabaseManager.authenticateUser(username, password);
        if (wishListID != -1) {
            return "redirect:/wishlist?wishListID=" + wishListID;
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
            return "redirect:/wishlist?wishListID=" + wishListID;
        } else {

            return "redirect:/loginError";
        }
    }
    @GetMapping("/wishlist")
    public String showWishlist(@RequestParam("wishListID") int wishListID, Model model) {
        List<Wish> wishes = wishList.loadWishList(wishListID);
        model.addAttribute("wishes", wishes);
        model.addAttribute("wishListID", wishListID);
        return "wishlist";
    }

    @GetMapping("/rediger/{wishID}/{wishListID}/{title}/{beskrivelse}/{link}")
    public String redigerWish(@PathVariable("wishID") int wishID, @PathVariable("wishListID") int wishListID, @PathVariable("title") String title, @PathVariable("beskrivelse") String beskrivelse, @PathVariable("link") String link, Model model) {

        model.addAttribute("wishID", wishID);
        model.addAttribute("wishListID", wishListID);
        model.addAttribute("title", title);
        model.addAttribute("beskrivelse", beskrivelse);
        model.addAttribute("link", link);
        return "rediger";
    }

    @GetMapping("/sharepage")
    public String shareWishlist(@RequestParam("wishListID") int wishListID, Model model) {
        List<Wish> wishes = wishList.loadWishList(wishListID);
        model.addAttribute("wishes", wishes);
        model.addAttribute("wishListID", wishListID);

        return "sharepage";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int deleteId, @RequestParam("wishListID") int wishListID){
        wishlist.deletewishID(deleteId);
        return "redirect:/wishlist?wishListID=" + wishListID;
    }

    @PostMapping("/update")
    public String updateWish(
            @RequestParam("wishID") int wishID,
            @RequestParam("wishListID") int wishListID,
            @RequestParam("title") String title,
            @RequestParam("beskrivelse") String beskrivelse,
            @RequestParam("link") String link
    ){

        if (link.isEmpty()) {
            link = "Link:";
        }

            Wish wish = new Wish(wishID, wishListID, title, beskrivelse, link);
            wishlist.update(wish);

        return "redirect:/wishlist?wishListID=" + wishListID;
    }

}
