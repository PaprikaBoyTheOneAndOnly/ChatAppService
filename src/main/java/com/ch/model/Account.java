package com.ch.model;

public class Account {
    private String username;
    private String password;
    private boolean logedIn;

    public Account(){
        this("","");
    }

    public Account(String username, String password){
        this.username = username;
        this.password = password;
        this.logedIn = false;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void login(){
        this.logedIn = true;
    }

    public void logout() {
        this.logedIn = false;
    }

    public boolean isLogedIn() {
        return logedIn;
    }
}
