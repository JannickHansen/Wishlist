package com.example.wishlist.model;

public class Wish {
    int wishID;
    int wishListID;
    String title;
    String beskrivelse;
    String billede;
    public Wish(int wishListID, String title, String beskrivelse, String billede) {
        this.wishListID = wishListID;
        this.title = title;
        this.beskrivelse = beskrivelse;
        this.billede = billede;
    }
    public Wish() {}

    public int getWishID() {return wishID;}
    public int getWishListID() {return wishListID;}
    public String getTitle() {return title;}
    public String getBeskrivelse() {return beskrivelse;}
    public String getBillede() {return billede;}
    public int setWishID(int wishID) {
        this.wishID = wishID;
        return this.wishID;
    }
    public int setWishListID(int wishListID) {
        this.wishListID = wishListID;
        return this.wishListID;
    }
    public String setTitle(String title) {
        this.title = title;
        return this.title;
    }
    public String setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
        return this.beskrivelse;
    }
    public String setBillede(String billede) {
        this.billede = billede;
        return this.billede;
    }
}
