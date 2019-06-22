package com.me.ch.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Account {
    private String UUID;
    private String username;
    private String password;

    // key = username of other account
    private HashMap<String, List<Message>> chats;

    public Account(){
        this("","");
    }

    public Account(String username, String password){
        this.username = username;
        this.password = password;
        this.UUID = "";
        this.chats = new HashMap<>();
    }

    public String getPassword(){
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void addMessage(Message message) {
        String chattedUser = message.getFrom() == this.username? message.getTo(): message.getFrom();

        if(this.chats.get(chattedUser) == null){
            this.chats.put(chattedUser, new ArrayList<>());
        }
        this.chats.get(chattedUser).add(message);
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
