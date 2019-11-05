package com.me.ch.model;

import java.util.ArrayList;

public class Chat {
    private String chatWith;
    private ArrayList<Message> addressableList;

    public Chat() {
        this("", new ArrayList<>());
    }

    public Chat(String chatWith) {
        this(chatWith, new ArrayList<>());
    }

    public Chat(String chatWith, ArrayList<Message> messages) {
        this.chatWith = chatWith;
        this.addressableList = messages;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String chatWith) {
        this.chatWith = chatWith;
    }

    public ArrayList<Message> getAddressableList() {
        return addressableList;
    }

    public void setAddressableList(ArrayList<Message> addressableList) {
        this.addressableList = addressableList;
    }

    public void addMessage(Message message) {
        this.addressableList.add(message);
    }
}
