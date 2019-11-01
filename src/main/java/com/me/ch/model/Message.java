package com.me.ch.model;

public class Message extends Addressable {
    private String text;

    public Message() {
        this("", "", "");
    }

    public Message(String from, String to, String text) {
        super(from, to);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
