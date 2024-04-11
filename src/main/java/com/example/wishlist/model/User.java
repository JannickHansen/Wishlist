package com.example.wishlist.model;

public class User {
    int userID;
    String username;
    String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
