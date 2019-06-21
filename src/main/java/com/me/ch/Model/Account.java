package com.me.ch.Model;

public class Account {
    private String username;
    private String password;

    public Account(){
        this("","");
    }

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
