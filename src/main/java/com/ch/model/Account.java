package com.ch.model;

public class Account {
    private String name;
    private boolean logedIn;

    public Account(String name){
        this.name = name;
        this.logedIn = false;
    }

    public String getName() {
        return name;
    }

    public void login(){
        this.logedIn = true;
    }

    public void logout() {
        this.logedIn = false;
    }
}
