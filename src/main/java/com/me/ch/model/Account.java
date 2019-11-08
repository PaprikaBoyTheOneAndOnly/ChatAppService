package com.me.ch.model;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;

    private ArrayList<Chat> chats;

    public Account() {
        this("", "");
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.chats = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    /*public void addMessage(Message message) {
        String chatWith = this.getUsername().equals(message.getTo()) ? message.getFrom() : message.getTo();
        boolean isExistingChat = false;
        for (Chat chat : this.chats) {
            if (chat.getChatWith().equals(chatWith)) {
                chat.addMessage(message);
                isExistingChat = true;
            }
        }

        if (!isExistingChat) {
            ArrayList<Message> messages = new ArrayList<>();
            messages.add(message);
            this.chats.add(new Chat(chatWith, messages));
        }
    }*/

    public ArrayList<Chat> getChats() {
        if (this.chats == null) {
            this.chats = new ArrayList<>();
        }
        return this.chats;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Account)) {
            return false;
        }

        Account account = (Account) o;

        if (!username.equals(account.username)) {
            return false;
        }
        return password.equals(account.password);
    }
}
