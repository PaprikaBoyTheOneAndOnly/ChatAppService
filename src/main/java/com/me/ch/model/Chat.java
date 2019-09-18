package com.me.ch.model;

import java.util.ArrayList;

public class Chat {
    private String chatWith;
    private ArrayList<Message> messages;

    public Chat() {
        this("", new ArrayList<>());
    }

    public Chat(String chatWith) {
        this(chatWith, new ArrayList<>());
    }

    public Chat(String chatWith, ArrayList<Message> messages) {
        this.chatWith = chatWith;
        this.messages = messages;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String chatWith) {
        this.chatWith = chatWith;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
}
