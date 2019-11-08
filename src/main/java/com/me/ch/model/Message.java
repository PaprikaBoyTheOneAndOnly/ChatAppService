package com.me.ch.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message extends Addressable {
    private String text;

    public Message() {
        this("", "", "", LocalDateTime.now());
    }

    public Message(String from, String to, String text, LocalDateTime timeSent) {
        super(from, to, timeSent);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        if (!super.equals(o)) return false;

        Message message = (Message) o;

        return Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" + "text='" + text + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", timeSent=" + timeSent +
                '}';
    }
}
