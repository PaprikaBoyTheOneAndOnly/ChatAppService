package com.ch.model;

public class Account {
    private String username;
    private String password;
    private boolean loggedIn;

    public Account(){
        this("","");
    }

    public Account(String username, String password){
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void login(){
        this.loggedIn = true;
    }

    public void logout() {
        this.loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
