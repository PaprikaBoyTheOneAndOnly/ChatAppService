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

    public Account() {
        this("", "");
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.UUID = "";
        this.chats = new HashMap<>();
    }

    public String getPassword() {
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
        String chatToAdd = this.getUsername().equals(message.getTo()) ? message.getFrom() : message.getTo();

        if (this.chats.get(chatToAdd) == null)
            this.chats.put(chatToAdd, new ArrayList<>());

        this.chats.get(chatToAdd).add(message);
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public HashMap<String, List<Message>> getChats() {
        return this.chats;
    }
}
