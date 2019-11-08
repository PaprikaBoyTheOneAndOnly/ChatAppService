package com.me.ch.model;

import java.util.ArrayList;

public class Chat {
    private String chatWith;
    private ArrayList<Addressable> addressableList;

    public Chat() {
        this("", new ArrayList<>());
    }

    public Chat(String chatWith) {
        this(chatWith, new ArrayList<>());
    }

    public Chat(String chatWith, ArrayList<Addressable> messages) {
        this.chatWith = chatWith;
        this.addressableList = messages;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String chatWith) {
        this.chatWith = chatWith;
    }

    public ArrayList<Addressable> getAddressableList() {
        return addressableList;
    }

    public void setAddressableList(ArrayList<Addressable> addressableList) {
        this.addressableList = addressableList;
    }

    public void addAddressable(Addressable addressable) {
        this.addressableList.add(addressable);
    }
}
