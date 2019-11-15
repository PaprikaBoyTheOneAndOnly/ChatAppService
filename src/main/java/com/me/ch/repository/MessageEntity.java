package com.me.ch.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String from_user;
    private String to_user;
    private String message;
    private LocalDateTime sent_time;

    public MessageEntity() {
    }

    public MessageEntity(String from_user, String to_user, String message, LocalDateTime sent_time) {
        this.from_user = from_user;
        this.to_user = to_user;
        this.message = message;
        this.sent_time = sent_time;
    }

    public LocalDateTime getSent_time() {
        return sent_time;
    }

    public void setSent_time(LocalDateTime sent_time) {
        this.sent_time = sent_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
