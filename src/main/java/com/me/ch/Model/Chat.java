package com.me.ch.Model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private String chatWith;
    private List<Message> messages;

    public Chat() { }

    public Chat(String chatWith) {
        this.chatWith = chatWith;
        this.messages = new ArrayList<>();
    }

    public Chat(String chatWith, List<Message> messages) {
        this.chatWith = chatWith;
        this.messages = messages;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String chatWith) {
        this.chatWith = chatWith;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        System.out.println(message);
        System.out.println(this.messages);
        this.messages.add(message);
    }
}
