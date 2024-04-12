package com.example.wishlist.model;

public class Wish {
    int wishID;
    int wishListID;
    String title;
    String beskrivelse;
    String billede;
    String link;
    public Wish(int wishListID, String title, String beskrivelse, String billede, String link) {
        this.wishListID = wishListID;
        this.title = title;
        this.beskrivelse = beskrivelse;
        this.billede = billede;
        this.link = link;
    }

    public int getWishID() {return wishID;}
    public int getWishListID() {return wishListID;}
    public String getTitle() {return title;}
    public String getBeskrivelse() {return beskrivelse;}
    public String getBillede() {return billede;}
    public String getLink() {return link;}
}
