package com.me.ch.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Chat {
    private String chatWith;
    private ArrayList<Message> messages;

    private Logger logger = LoggerFactory.getLogger(Chat.class);

    public Chat() { }

    public Chat(String chatWith) {
        this.chatWith = chatWith;
        this.messages = new ArrayList<>();
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
        if(this.messages== null) {
            this.messages = new ArrayList<>();
        }

        this.logger.info(message.toString());
        this.messages.add(message);
    }
}
